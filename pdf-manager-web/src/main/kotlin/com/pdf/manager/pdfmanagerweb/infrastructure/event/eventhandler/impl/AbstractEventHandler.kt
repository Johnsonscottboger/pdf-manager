package com.pdf.manager.pdfmanagerweb.infrastructure.event.eventhandler.impl

import com.pdf.manager.pdfmanagerweb.infrastructure.event.IEvent
import com.pdf.manager.pdfmanagerweb.infrastructure.event.IEventResult
import com.pdf.manager.pdfmanagerweb.infrastructure.event.eventhandler.IEventHandler

abstract class AbstractEventHandler<TEvent : IEvent, TResult : Any?> : IEventHandler<TEvent> {
    abstract fun handle(event: IEventResult<TResult>): TResult
}