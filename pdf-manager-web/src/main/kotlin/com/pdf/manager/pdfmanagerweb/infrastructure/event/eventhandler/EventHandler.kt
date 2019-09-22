package com.pdf.manager.pdfmanagerweb.infrastructure.event.eventhandler

import com.pdf.manager.pdfmanagerweb.infrastructure.event.IEvent
import kotlin.reflect.KClass


/**
 * 标注当前可目标作为 [IEventHandler] 的实例
 */
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.CLASS)
annotation class EventHandler(val event: KClass<IEvent> = IEvent::class)
