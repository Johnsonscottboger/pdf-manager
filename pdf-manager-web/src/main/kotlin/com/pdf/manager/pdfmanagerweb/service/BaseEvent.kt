package com.pdf.manager.pdfmanagerweb.service

import com.pdf.manager.pdfmanagerweb.domain.dto.Response
import com.pdf.manager.pdfmanagerweb.infrastructure.event.IEventResult

/**
 * 基础事件, 包括 [Response] 响应结果
 */
abstract class BaseEvent : IEventResult<Response> {

    /**
     * 获取操作
     */
    abstract val operation: String

    /**
     * 获取结果
     */
    override var result: Response = Response()
}