package com.pdf.manager.pdfmanagerweb.service.doc

import com.pdf.manager.pdfmanagerweb.domain.dataobject.Doc
import com.pdf.manager.pdfmanagerweb.domain.dataobject.Pdf
import com.pdf.manager.pdfmanagerweb.domain.dto.ConverterType
import com.pdf.manager.pdfmanagerweb.domain.dto.PdfStatus
import com.pdf.manager.pdfmanagerweb.domain.dto.Response
import com.pdf.manager.pdfmanagerweb.infrastructure.event.eventhandler.EventHandler
import com.pdf.manager.pdfmanagerweb.service.doc.event.GetWordEvent
import com.pdf.manager.pdfmanagerweb.service.file.IFileService
import com.pdf.manager.pdfmanagerweb.service.pdf.IPdfService
import com.pdf.manager.pdfmanagerweb.service.pdf.event.ConvertToWordEvent
import org.springframework.stereotype.Component

/**
 * Doc 事件处理程序
 */
@Component
class DocEventHandler(val pdfService: IPdfService,
                      val docService: IDocService,
                      val fileService: IFileService,
                      val converter: IDocConvertFactory) {

    /**
     * 将 PDF 转换为 Word
     */
    @EventHandler
    fun convertPdfToWord(event: ConvertToWordEvent): Response<Unit> {
        val pdf = this.pdfService.getById(event.pdfId)
        return if (pdf != null) {
            pdf.status = PdfStatus.CONVERTING.ordinal
            try {
                converter.convertToDoc(ConverterType.MS_WORD, pdf.id, pdf.name, pdf.location)
            } catch (ex: Exception) {
                pdf.status = PdfStatus.FAILED.ordinal
            } finally {
                this.pdfService.update(pdf)
            }
            Response.succ("将 PDF 转换为 Word")
        } else {
            Response.fail("将 PDF 转换为 Word")
        }
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