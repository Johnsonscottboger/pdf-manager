package com.pdf.manager.pdfmanagerweb.service.doc

import com.pdf.manager.pdfmanagerweb.domain.dataobject.Doc
import com.pdf.manager.pdfmanagerweb.domain.dto.Response
import com.pdf.manager.pdfmanagerweb.infrastructure.event.eventhandler.EventHandler
import com.pdf.manager.pdfmanagerweb.service.doc.event.GetWordEvent
import com.pdf.manager.pdfmanagerweb.service.file.IFileService
import com.pdf.manager.pdfmanagerweb.service.pdf.event.ConvertToWordEvent
import org.springframework.stereotype.Component

/**
 * Doc 事件处理程序
 */
@Component
class DocEventHandler(val docService: IDocService, val fileService: IFileService) {

    /**
     * 将 PDF 转换为 Word
     */
    @EventHandler
    fun convertPdfToWord(event: ConvertToWordEvent): Response<Unit> {
        throw NotImplementedError()
    }

    /**
     * 获取指定 PDF 的 Word 文件
     */
    @EventHandler
    fun getWord(event: GetWordEvent): Response<Doc> {
        return try {
            val doc = this.docService.getByPdfId(event.pdfId).maxBy { p -> p.score }!!
            this.fileService.getFile(doc.location)
            Response.succ(event.operation, data = doc)
        } catch (ex: Exception) {
            Response.fail(event.operation, exception = ex)
        }
    }
}