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

    /**
     * 将指定的 PDF 文件转换为 Word
     * @param pdfId 指定的 PDF
     * @param fileName 指定的 PDF 文件名
     * @param location 指定的文件位置
     */
    @ResponseBody
    @PostMapping("/{pdfId}/convert")
    fun convert(@PathVariable("pdfId") pdfId: String, @RequestParam("fileName") fileName: String, @RequestParam("location") location: String): Response<Unit> {
        val operation = "PDF 转换为 Word"
        return try {
            this.docService.convert(pdfId, fileName, location)
            Response.succ(operation)
        } catch (ex: Exception) {
            Response.fail(operation, exception = ex)
        }
    }
}