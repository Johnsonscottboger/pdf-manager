package com.pdf.manager.pdfmanagerweb.infrastructure.event.eventbus.impl

import com.pdf.manager.pdfmanagerweb.infrastructure.event.IEvent
import com.pdf.manager.pdfmanagerweb.infrastructure.event.IEventResult
import com.pdf.manager.pdfmanagerweb.infrastructure.event.eventbus.IEventBus
import com.pdf.manager.pdfmanagerweb.infrastructure.event.eventhandler.IEventHandler
import com.pdf.manager.pdfmanagerweb.infrastructure.event.eventhandler.IEventHandlerFactory
import com.pdf.manager.pdfmanagerweb.infrastructure.event.eventstore.IEventStore
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

/**
 * 事件总线, 默认实现
 */
@Component
class EventBus(val eventHandlerFactory: IEventHandlerFactory, val eventStore: IEventStore) : IEventBus {

    /**
     * 初始化
     */
    @PostConstruct
    override fun initialize() {
        val eventHandlers = this.eventHandlerFactory.getAllHandlers()
        for (eventHandler in eventHandlers) {
            this.eventStore.subscribe(eventHandler.key, eventHandler.value.toList())
        }
    }

    /**
     * 订阅事件
     * @param eventClass 指定的事件
     * @param handlerClass 指定的事件处理程序
     */
    override fun subscribe(eventClass: Class<IEvent>, handlerClass: IEventHandler<IEvent>) {
        this.eventStore.subscribe(eventClass, handlerClass)
    }

    /**
     * 取消订阅
     * @param eventClass 指定的事件
     * @param handlerClass 指定的事件处理程序
     */
    override fun unsubscribe(eventClass: Class<IEvent>, handlerClass: IEventHandler<IEvent>) {
        this.eventStore.unsubscribe(eventClass, handlerClass)
    }

    /**
     * 发布事件
     * @param event 指定的事件实例
     */
    override fun publish(event: IEvent) {
        val handlers = this.eventStore.getEventHandlerClass(event::class.java)
        if(handlers.isEmpty())
            throw NoSuchElementException("Not found any event handler.")
        for (handler in handlers) {
            handler.handle(event)
        }
    }

    /**
     * 发布事件, 并获取事件的返回值, 但存在多个事件处理程序时, 只保留最后一个处理程序的返回值
     * @param event 指定的事件实例
     */
    override fun <TResult> publish(event: IEventResult<TResult>): TResult {
        val handlers = this.eventStore.getEventHandlerClass(event::class.java)
        if(handlers.isEmpty())
            throw NoSuchElementException("Not found any event handler.")
        for (handler in handlers) {
            handler.handle(event)
        }
        return event.result
    }
}