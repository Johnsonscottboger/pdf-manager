package com.pdf.manager.pdfmanagerweb.service.file

import com.pdf.manager.pdfmanagerweb.domain.dto.Response
import com.pdf.manager.pdfmanagerweb.service.file.dto.AddPdfFile
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.InputStream

/**
 * 文件服务
 */
@FeignClient("pdf-manager-file", path = "/file")
interface IFileService {

    /**
     * 首页
     */
    @GetMapping("/index")
    fun index(): String

    /**
     * 添加文件
     */
    @PostMapping("/", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun addFile(@RequestParam("fileName") fileName: String, inputStream: MultipartFile): AddPdfFile

    /**
     * 获取文件
     */
    @GetMapping("/")
    fun getFile(@RequestParam("location") location: String): ByteArray

    /**
     * 删除文件
     */
    @DeleteMapping("/{location}")
    fun delete(@PathVariable("location") location: String)
}