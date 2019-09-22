package com.pdf.manager.pdfmanagerweb.web.controller

import com.pdf.manager.pdfmanagerweb.infrastructure.event.eventbus.IEventBus
import com.pdf.manager.pdfmanagerweb.service.pdf.event.GetUserAllPdfEvent
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

/**
 * PDF 管理控制器
 */
@Controller
@RequestMapping("/pdf")
class PdfController(val eventBus: IEventBus) {

    /**
     * 首页
     */
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
}