package com.pdf.manager.pdfmanagerweb.service.cnv

import com.pdf.manager.pdfmanagerweb.domain.dto.Response
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

/**
 * 使用 Microsoft Word 将 PDF 转换为 Word
 */
@FeignClient("pdf-manager-external-msword", path = "/doc")
interface IMSWordConvert {

    /**
     * 将指定的 PDF 文件转换为 Word
     * @param pdfId 指定的 PDF
     * @param fileName 指定的 PDF 文件名
     * @param location 指定的文件位置
     */
    @PostMapping("/{pdfId}/convert")
    fun convert(@PathVariable("pdfId") pdfId: String, @RequestParam("fileName") fileName: String, @RequestParam("location") location: String)
}