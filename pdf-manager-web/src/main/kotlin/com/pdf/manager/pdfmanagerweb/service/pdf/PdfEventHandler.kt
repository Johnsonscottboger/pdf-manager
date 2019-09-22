package com.pdf.manager.pdfmanagerweb.service.pdf

import com.netflix.discovery.converters.Auto
import com.pdf.manager.pdfmanagerweb.domain.dto.Response
import com.pdf.manager.pdfmanagerweb.infrastructure.event.eventhandler.EventHandler
import com.pdf.manager.pdfmanagerweb.service.pdf.event.GetUserAllPdfEvent
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.annotation.Resource

/**
 * PDF 服务事件处理程序
 */
@Component
class PdfEventHandler() {
    private val log = LoggerFactory.getLogger(this::class.java)

    @Autowired
    private lateinit var pdfService: IPdfService

    /**
     * 获取所有用户的 PDF 文件
     */
    @EventHandler
    public fun handler(event: GetUserAllPdfEvent): Response {
        return try {
            val pdfs = this.pdfService.getByUserId(event.userId)
            Response.succ(event.operation, data = pdfs)
        } catch (ex: Exception) {
            Response.fail(event.operation, data = ex)
        }
    }
}