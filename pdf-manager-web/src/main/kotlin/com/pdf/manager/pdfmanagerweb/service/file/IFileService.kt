package com.pdf.manager.pdfmanagerweb.service.file

import com.pdf.manager.pdfmanagerweb.service.file.dto.AddPdfFile
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.InputStream

/**
 * 文件服务
 */
interface IFileService {
    fun addFile(fileName: String, inputStream: MultipartFile): AddPdfFile

    fun getFile(location: String): File

    fun delete(location: String)
}