package com.pdf.manager.pdfmanagerweb.web.controller

import com.pdf.manager.pdfmanagerweb.infrastructure.event.eventbus.IEventBus
import com.pdf.manager.pdfmanagerweb.service.doc.event.GetWordEvent
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

/**
 * Doc 管理控制器
 */
@Controller
@RequestMapping("/doc")
class DocController(val eventBus: IEventBus) {

    /**
     * 首页
     */
    @ResponseBody
    @GetMapping("/index")
    fun index() : String {
        return "/doc/index"
    }

    /**
     * 获取 PDF 的 Word 文档
     */
    @ResponseBody
    @GetMapping("/{pdfId}/doc")
    fun getDoc(@PathVariable("pdfId") pdfId: String) = eventBus.publish(GetWordEvent(pdfId))
}