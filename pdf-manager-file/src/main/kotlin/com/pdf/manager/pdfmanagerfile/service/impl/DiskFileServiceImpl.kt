package com.pdf.manager.pdfmanagerfile.service.impl

import com.pdf.manager.pdfmanagerfile.service.IFileService
import com.pdf.manager.pdfmanagerfile.service.dto.FileResult
import com.pdf.manager.pdfmanagerfile.util.FileUtils
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.nio.file.Paths

/**
 * 基础磁盘的文件服务实现
 */
@Component
class DiskFileServiceImpl : IFileService {
    private val ROOT = "C:\\pdf-manager"

    /**
     * 添加文件
     * @param fileName 指定的文件名
     * @param file 指定的文件
     * @return 添加文件结果
     */
    override fun addFile(fileName: String, file: MultipartFile): FileResult {
        val md5 = FileUtils.getMD5(file.inputStream)
        val path = Paths.get(this.ROOT, fileName).toString()
        val f = File(path)
        if (!f.parentFile.exists())
            f.parentFile.mkdirs()
        file.transferTo(f)
        return FileResult(md5, fileName)
    }

    /**
     * 获取文件
     * @param location 指定的文件路径
     * @return 文件
     */
    override fun getFile(location: String): File? {
        val file = File(Paths.get(this.ROOT, location).toString())
        return if (file.exists()) file else null
    }

    /**
     * 删除文件
     * @param location 指定的文件路径
     */
    override fun delete(location: String) {
        val file = File(Paths.get(this.ROOT, location).toString())
        if (file.exists() && file.isFile)
            file.delete()
    }
}