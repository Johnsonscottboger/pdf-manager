package com.pdf.manager.pdfmanagerweb.infrastructure.event.eventstore

import com.pdf.manager.pdfmanagerweb.infrastructure.event.IEvent
import com.pdf.manager.pdfmanagerweb.infrastructure.event.eventhandler.IEventHandler

/**
 * 事件存储
 */
interface IEventStore {

    /**
     * 订阅事件
     * @param eventClass 指定的事件
     * @param handlerClass 指定的事件处理程序
     */
    fun subscribe(eventClass: Class<IEvent>, handlerClass: IEventHandler<IEvent>)

    /**
     * 订阅事件
     * @param eventClass 指定的事件
     * @param handlerClasses 指定的事件处理程序
     */
    fun subscribe(eventClass: Class<IEvent>, handlerClasses: List<IEventHandler<IEvent>>)

    /**
     * 取消订阅
     * @param eventClass 指定的事件
     * @param handlerClass 指定的事件处理程序
     */
    fun unsubscribe(eventClass: Class<IEvent>, handlerClass: IEventHandler<IEvent>)

    /**
     * 获取已订阅事件的处理程序
     * @param eventClass 指定的事件
     */
    fun getEventHandlerClass(eventClass: Class<out IEvent>): List<IEventHandler<IEvent>>

    /**
     * 清空所有事件及其处理程序
     */
    fun clear()

    /**
     * 清空指定事件的处理程序
     * @param eventClass 指定的事件
     */
    fun clear(eventClass: Class<IEvent>)
}