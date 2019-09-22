package com.pdf.manager.pdfmanagerweb.infrastructure.event.eventhandler

import com.pdf.manager.pdfmanagerweb.infrastructure.event.IEvent

/**
 * 事件处理程序工厂
 */
interface IEventHandlerFactory {

    /**
     * 获取指定事件的处理程序, 当存在多个时, 返回找到的第一个实例
     */
    fun getHandler(eventClass: Class<IEvent>): IEventHandler<IEvent>

    /**
     * 获取指定事件的所有处理程序
     */
    fun getHandlers(eventClass: Class<IEvent>): List<IEventHandler<IEvent>>

    /**
     * 获取所有事件及其处理程序
     */
    fun getAllHandlers(): Map<Class<IEvent>, Set<IEventHandler<IEvent>>>
}