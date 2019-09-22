package com.pdf.manager.pdfmanagerweb.infrastructure.event.eventbus

import com.pdf.manager.pdfmanagerweb.infrastructure.event.IEvent
import com.pdf.manager.pdfmanagerweb.infrastructure.event.IEventResult
import com.pdf.manager.pdfmanagerweb.infrastructure.event.eventhandler.IEventHandler

/**
 * 事件总线
 */
interface IEventBus {

    /**
     * 初始化
     */
    fun initialize()

    /**
     * 订阅事件
     * @param eventClass 指定的事件
     * @param handlerClass 指定的事件处理程序
     */
    fun subscribe(eventClass: Class<IEvent>, handlerClass: IEventHandler<IEvent>)

    /**
     * 取消订阅
     * @param eventClass 指定的事件
     * @param handlerClass 指定的事件处理程序
     */
    fun unsubscribe(eventClass: Class<IEvent>, handlerClass: IEventHandler<IEvent>)

    /**
     * 发布事件
     * @param event 指定的事件实例
     */
    fun publish(event: IEvent)

    /**
     * 发布事件, 并获取事件的返回值, 但存在多个事件处理程序时, 只保留最后一个处理程序的返回值
     * @param event 指定的事件实例
     */
    fun <TResult> publish(event: IEventResult<TResult>): TResult
}