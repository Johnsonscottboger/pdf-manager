package com.pdf.manager.pdfmanagerfile.service

import com.pdf.manager.pdfmanagerfile.service.dto.FileResult
import org.springframework.web.multipart.MultipartFile
import java.io.File

/**
 * 文件服务
 */
interface IFileService {
    /**
     * 添加文件
     * @param fileName 指定的文件名
     * @param file 指定的文件
     * @return 添加文件结果
     */
    fun addFile(fileName: String, file: MultipartFile): FileResult

    /**
     * 获取文件
     * @param location 指定的文件路径
     * @return 文件
     */
    fun getFile(location: String): File?

    /**
     * 删除文件
     * @param location 指定的文件路径
     */
    fun delete(location: String)
}