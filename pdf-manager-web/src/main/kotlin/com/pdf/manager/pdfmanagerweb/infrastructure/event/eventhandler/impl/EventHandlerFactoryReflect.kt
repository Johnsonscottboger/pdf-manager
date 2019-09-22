package com.pdf.manager.pdfmanagerweb.infrastructure.event.eventhandler.impl

import com.pdf.manager.pdfmanagerweb.infrastructure.event.IEvent
import com.pdf.manager.pdfmanagerweb.infrastructure.event.IEventResult
import com.pdf.manager.pdfmanagerweb.infrastructure.event.eventhandler.EventHandler
import com.pdf.manager.pdfmanagerweb.infrastructure.event.eventhandler.IEventHandler
import com.pdf.manager.pdfmanagerweb.infrastructure.event.eventhandler.IEventHandlerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.ComponentScan
import org.springframework.stereotype.Component
import java.io.File
import java.lang.reflect.Modifier
import java.net.JarURLConnection
import java.net.URLDecoder
import java.util.jar.JarFile
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.isSubclassOf

/**
 * 事件处理器工厂, 使用反射实现
 */
@Component
@ComponentScan(basePackages = ["com.pdf.manager.pdfmanagerweb"])
class EventHandlerFactoryReflect : IEventHandlerFactory {

    @Autowired
    private lateinit var context: ApplicationContext

    /**
     * 获取指定事件的处理程序, 当存在多个时, 返回找到的第一个实例
     */
    override fun getHandler(eventClass: Class<IEvent>): IEventHandler<IEvent> {
        val handlers = this.getHandlers(eventClass)
        if (handlers.isEmpty())
            throw NoSuchElementException()
        return handlers.first()
    }

    /**
     * 获取指定事件的所有处理程序
     */
    override fun getHandlers(eventClass: Class<IEvent>): List<IEventHandler<IEvent>> {
        return this.getAllHandlers().getOrDefault(eventClass, hashSetOf()).toList()
    }

    /**
     * 获取所有事件及其处理程序
     */
    override fun getAllHandlers(): Map<Class<IEvent>, Set<IEventHandler<IEvent>>> {
        val classes = findEventHandlerImplClasses(findClasses())
        val map = HashMap<Class<IEvent>, HashSet<IEventHandler<IEvent>>>()
        for (clazz in classes) {
            try {
                val instance = try {
                    this.context.getBean(clazz)
                } catch (ex: Exception) {
                    clazz.getDeclaredConstructor().newInstance()
                }
                for (method in clazz.declaredMethods.filter { p -> !p.isBridge }) {
                    val event = method.parameters.firstOrNull { parameter -> parameter.type.kotlin.isSubclassOf(IEvent::class) }
                    if (event != null) {
                        map.getOrPut(event.type as Class<IEvent>, { hashSetOf() }).add(object : AbstractEventHandler<IEvent, Any?>() {
                            override fun handle(event: IEvent) {
                                if (event is IEventResult<*>)
                                    this.handle(event as IEventResult<Any?>)
                                else
                                    method.invoke(instance, event)
                            }

                            override fun handle(event: IEventResult<Any?>): Any? {
                                val result = method.invoke(instance, event)
                                if (result != null)
                                    event.result = result
                                return result
                            }
                        })
                    }
                }
            } catch (ex: NoSuchMethodException) {
                continue
            }
        }
        return map
    }

    /**
     * 扫描指定包下的所有 [IEventHandler] 实现类
     * @return [IEventHandler] 的实现类
     */
    private fun findEventHandlerImplClasses(list: List<Class<*>>): List<Class<*>> {
        val eventHandlerAnnotation = EventHandler::class.java
        return list.filter { p ->
            !p.isInterface
                    && !Modifier.isAbstract(p.modifiers)
                    && (IEventHandler::class.java.isAssignableFrom(p)
                    || p.isAnnotationPresent(eventHandlerAnnotation)
                    || p.declaredMethods.any { c -> c.isAnnotationPresent(eventHandlerAnnotation) })
        }
    }

    /**
     * 扫描指定包下的所有 `class` 文件
     */
    private fun findClasses(): List<Class<*>> {
        val clazz = this::class
        val basePackages = clazz.findAnnotation<ComponentScan>()?.basePackages
        if (basePackages != null) {
            val list = mutableListOf<Class<*>>()
            for (basePackage in basePackages) {
                val packageName = basePackage.replace('.', '/')
                val urls = clazz.java.classLoader.getResources(packageName)
                for (url in urls) {
                    val classes = mutableListOf<Class<*>>()
                    when (url.protocol) {
                        "file" -> {
                            val fileName = URLDecoder.decode(url.file, "UTF-8")
                            findClassesByFile(packageName, fileName, classes)
                        }
                        "jar" -> {
                            val jar = (url.openConnection() as JarURLConnection).jarFile
                            findClassesByJar(packageName, jar, classes)
                        }
                        else -> throw IllegalStateException()
                    }
                    list.addAll(classes)
                }
            }
            return list
        }
        return listOf()
    }

    /**
     * 扫描指定包下的所有 `class` 文件
     * @param packageName 指定的包名称
     * @param jar 指定的 [JarFile] 实例
     * @return 指定包下的所有 [Class] 实例
     */
    private fun findClassesByJar(packageName: String, jar: JarFile?, list: MutableList<Class<*>>) {
        if (jar == null) return
        val pkgDir = packageName.replace('.', '/')
        list.addAll(jar.entries()
                .asSequence()
                .map { p -> p.name.trimStart('/') to p }
                .filter { p -> p.first.startsWith(pkgDir) && p.first.endsWith(".class") && !p.second.isDirectory }
                .map { p -> loadClazz(p.first.substring(0, p.first.length - 6)) }
                .toList())
    }

    /**
     * 扫描指定文件下的所有 `class` 文件
     * @param packageName 指定的包名称
     * @param fileName 指定的文件名
     * @return 指定的包下的所有 [Class] 实例
     */
    private fun findClassesByFile(packageName: String, fileName: String, list: MutableList<Class<*>>) {
        val dir = File(fileName)
        if (!dir.exists() || !dir.isDirectory) return
        val files = dir.listFiles { pathname -> pathname.isDirectory || pathname.name.endsWith("class") }
        if (files == null || !files.any()) return
        for (file in files) {
            if (file.isDirectory) {
                findClassesByFile(packageName + "." + file.name, fileName + "/" + file.name, list)
                continue
            }

            val className = "${packageName.replace('/', '.')}.${file.name.substring(0, file.name.length - 6)}"
            list.add(loadClazz(className))
        }
    }

    /**
     * 加载 [Class]
     * @param classFullName 指定的类名称
     * @return [Class] 实例
     */
    private fun loadClazz(classFullName: String): Class<*> {
        return Thread.currentThread().contextClassLoader.loadClass(classFullName)
    }
}