package com.pdf.manager.pdfmanagerweb.service.file.impl

import com.pdf.manager.pdfmanagerweb.infrastructure.util.FileUtils
import com.pdf.manager.pdfmanagerweb.service.file.IFileService
import com.pdf.manager.pdfmanagerweb.service.file.dto.AddPdfFile
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.io.*
import java.nio.file.Paths

/**
 * 基于磁盘实现的文件服务
 */
@Component
class DiskFileServiceImpl : IFileService {
    private val ROOT = "C:\\pdf-manager"

    /**
     * 添加文件, 将指定的文件流保存到磁盘
     */
    override fun addFile(fileName: String, inputStream: MultipartFile): AddPdfFile {
        val md5 = FileUtils.getMD5(inputStream.inputStream)
        val path = Paths.get(this.ROOT, fileName).toString()
        val file = File(path)
        if (!file.parentFile.exists())
            file.parentFile.mkdirs()
        inputStream.transferTo(file)
        return AddPdfFile(md5, path)
    }

    /**
     * 获取文件, 使用指定的文件地址
     * @param location 指定的文件地址
     */
    override fun getFile(location: String): File {
        return File(Paths.get(this.ROOT, location).toString())
    }

    /**
     * 删除指定的文件
     * @param location 指定的文件
     */
    override fun delete(location: String) {
        val file = File(Paths.get(this.ROOT, location).toString())
        if (file.exists() && file.isFile)
            file.delete()
    }
}