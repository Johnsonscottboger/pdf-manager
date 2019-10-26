package com.pdf.manager.pdfmanagerweb.web.controller

import com.pdf.manager.pdfmanagerweb.infrastructure.event.eventbus.IEventBus
import com.pdf.manager.pdfmanagerweb.service.pdf.event.AddPdfEvent
import com.pdf.manager.pdfmanagerweb.service.pdf.event.ConvertToWordEvent
import com.pdf.manager.pdfmanagerweb.service.pdf.event.GetUserAllPdfEvent
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

/**
 * PDF 管理控制器
 */
@Controller
@RequestMapping("/pdf")
class PdfController(val eventBus: IEventBus) {

    /**
     * 首页
     */
    @ResponseBody
    @GetMapping("/index")
    fun index(): String {
        return "pdf/index"
    }

    /**
     * 获取指定用户的所有 PDF 文件
     */
    @ResponseBody
    @GetMapping("/{userId}/pdfs")
    fun getAllPDFs(@PathVariable("userId") userId: String) = eventBus.publish(GetUserAllPdfEvent(userId))

    /**
     * 添加 PDF 文件
     */
    @ResponseBody
    @PostMapping("/{userId}/add")
    fun addPdf(@PathVariable("userId") userId: String, file: MultipartFile) = eventBus.publish(AddPdfEvent(userId, file.originalFilename!!, file))

    /**
     * 将 PDF 转换为 Word
     */
    @ResponseBody
    @PostMapping("/{userId}/{pdfId}/convert")
    fun convertToWord(@PathVariable("userId") userId: String, @PathVariable("pdfId") pdfId: String) = eventBus.publish(ConvertToWordEvent(userId, pdfId))
}