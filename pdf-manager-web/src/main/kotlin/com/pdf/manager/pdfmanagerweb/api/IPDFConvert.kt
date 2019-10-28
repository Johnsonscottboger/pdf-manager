package com.pdf.manager.pdfmanagerweb.api

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.multipart.MultipartFile

/**
 * PDF 转换
 */
@FeignClient(name = "pdf-manager-external-msword")
interface IPDFConvert {

    /**
     * 将指定的 PDF 转换为 Word
     */
    @PostMapping("/doc/{pdfId}/convert")
    fun convert(@PathVariable("pdfId") pdfId: String, file: MultipartFile)
}