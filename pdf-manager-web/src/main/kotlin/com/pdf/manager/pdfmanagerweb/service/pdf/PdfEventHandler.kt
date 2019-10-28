package com.pdf.manager.pdfmanagerweb.service.pdf

import com.netflix.discovery.converters.Auto
import com.pdf.manager.pdfmanagerweb.domain.dataobject.Pdf
import com.pdf.manager.pdfmanagerweb.domain.dto.Response
import com.pdf.manager.pdfmanagerweb.infrastructure.event.eventhandler.EventHandler
import com.pdf.manager.pdfmanagerweb.service.file.IFileService
import com.pdf.manager.pdfmanagerweb.service.file.dto.AddPdfFile
import com.pdf.manager.pdfmanagerweb.service.pdf.event.AddPdfEvent
import com.pdf.manager.pdfmanagerweb.service.pdf.event.GetUserAllPdfEvent
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.io.File
import java.nio.file.Paths
import java.time.LocalDateTime
import java.util.*
import javax.annotation.Resource

/**
 * PDF 服务事件处理程序
 */
@Component
class PdfEventHandler(val pdfService: IPdfService, val fileService: IFileService) {
    private val log = LoggerFactory.getLogger(this::class.java)

    /**
     * 获取所有用户的 PDF 文件
     */
    @EventHandler
    fun getUserAllPdf(event: GetUserAllPdfEvent): Response<List<Pdf>> {
        return try {
            val pdf = this.pdfService.getByUserId(event.userId)
            Response.succ(event.operation, data = pdf)
        } catch (ex: Exception) {
            Response.fail(event.operation, exception = ex)
        }
    }

    /**
     * 添加 PDF 文件
     */
    @EventHandler
    fun addPdf(event: AddPdfEvent): Response<Unit> {
        return try {
            val (md5, location) = this.fileService.addFile(Paths.get(event.userId, event.fileName).toString(), event.inputStream)
            this.pdfService.addOrUpdate(Pdf(
                    id = UUID.randomUUID().toString(),
                    userId = event.userId,
                    md5 = md5,
                    name = event.fileName,
                    location = location,
                    status = 0,
                    result = "",
                    createDateTime = LocalDateTime.now()
            ))

            Response.succ(event.operation)
        } catch (ex: Exception) {
            Response.fail(event.operation, exception = ex)
        }
    }
}