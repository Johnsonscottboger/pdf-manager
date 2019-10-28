package com.pdf.manager.pdfmanagerexternalmsword.web.controller

import com.pdf.manager.pdfmanagerexternalmsword.domain.dto.Response
import com.pdf.manager.pdfmanagerexternalmsword.service.doc.IDocService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@Controller
@RequestMapping("/doc")
class HomeController(val docService: IDocService) {

    /**
     * 首页
     */
    @ResponseBody
    @GetMapping("/index")
    fun index(): String {
        return "/doc/index"
    }

    @ResponseBody
    @PostMapping("/{pdfId}/convert")
    fun convert(@PathVariable("pdfId") pdfId: String, file: MultipartFile): Response<Unit> {
        val operation = "PDF 转换为 Word"
        return try {
            this.docService.convert(pdfId, file)
            Response.succ(operation)
        } catch (ex: Exception) {
            Response.fail(operation, exception = ex)
        }
    }
}