package com.pdf.manager.pdfmanagerweb.infrastructure.event.eventhandler

import com.pdf.manager.pdfmanagerweb.infrastructure.event.IEvent

/**
 * 事件处理程序
 */
interface IEventHandler<in TEvent : IEvent> {

    /**
     * 处理指定的事件
     * @param event 指定的事件
     */
    fun handle(event: TEvent)
}