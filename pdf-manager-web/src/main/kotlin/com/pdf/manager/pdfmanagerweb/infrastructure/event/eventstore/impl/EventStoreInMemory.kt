package com.pdf.manager.pdfmanagerweb.infrastructure.event.eventstore.impl

import com.pdf.manager.pdfmanagerweb.infrastructure.event.IEvent
import com.pdf.manager.pdfmanagerweb.infrastructure.event.eventhandler.IEventHandler
import com.pdf.manager.pdfmanagerweb.infrastructure.event.eventstore.IEventStore
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap
import kotlin.reflect.jvm.jvmName

/**
 * 事件存储, 内存中实现
 */
@Component
class EventStoreInMemory : IEventStore {
    private val _handlers: ConcurrentMap<String, HashSet<IEventHandler<IEvent>>> = ConcurrentHashMap()

    /**
     * 订阅事件
     * @param eventClass 指定的事件
     * @param handlerClass 指定的事件处理程序
     */
    override fun subscribe(eventClass: Class<IEvent>, handlerClass: IEventHandler<IEvent>) {
        val key = eventClass.getEventKey()
        this._handlers.getOrPut(key, { hashSetOf() }).add(handlerClass)
    }

    /**
     * 订阅事件
     * @param eventClass 指定的事件
     * @param handlerClasses 指定的事件处理程序
     */
    override fun subscribe(eventClass: Class<IEvent>, handlerClasses: List<IEventHandler<IEvent>>) {
        val key = eventClass.getEventKey()
        this._handlers.getOrPut(key, { hashSetOf() }).addAll(handlerClasses)
    }

    /**
     * 取消订阅
     * @param eventClass 指定的事件
     * @param handlerClass 指定的事件处理程序
     */
    override fun unsubscribe(eventClass: Class<IEvent>, handlerClass: IEventHandler<IEvent>) {
        val key = eventClass.getEventKey()
        this._handlers[key]?.remove(handlerClass)
    }

    /**
     * 获取已订阅事件的处理程序
     * @param eventClass 指定的事件
     */
    override fun getEventHandlerClass(eventClass: Class<out IEvent>): List<IEventHandler<IEvent>> {
        val key = eventClass.getEventKey()
        return this._handlers[key]?.toList() ?: listOf()
    }

    /**
     * 清空所有事件及其处理程序
     */
    override fun clear() {
        this._handlers.clear()
    }

    /**
     * 清空指定事件的处理程序
     * @param eventClass 指定的事件
     */
    override fun clear(eventClass: Class<IEvent>) {
        val key = eventClass.getEventKey()
        this._handlers.remove(key)
    }

    /**
     * 获取事件的键
     * @return 事件的键
     */
    private fun Class<out IEvent>.getEventKey(): String{
        return this.canonicalName
    }
}

