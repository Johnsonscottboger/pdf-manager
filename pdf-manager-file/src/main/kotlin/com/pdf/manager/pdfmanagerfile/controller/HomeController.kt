package com.pdf.manager.pdfmanagerfile.controller

import com.pdf.manager.pdfmanagerfile.domain.dto.Response
import com.pdf.manager.pdfmanagerfile.service.IFileService
import com.pdf.manager.pdfmanagerfile.service.dto.FileResult
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.BufferedInputStream
import java.io.FileInputStream
import javax.servlet.http.HttpServletResponse

/**
 * 控制器
 */
@Controller
@RequestMapping("/file")
class HomeController(val fileService: IFileService) {

    /**
     * 首页
     */
    @ResponseBody
    @GetMapping("/index")
    fun index(): String {
        return "/file/index"
    }

    /**
     * 添加文件
     */
    @ResponseBody
    @PostMapping("/")
    fun add(@RequestParam("fileName") fileName: String, file: MultipartFile): FileResult {
        return try {
            this.fileService.addFile(fileName, file)
        } catch (ex: Exception) {
            FileResult("添加失败", ex.message!!)
        }
    }

    /**
     * 下载文件
     */
    @ResponseBody
    @GetMapping("/")
    fun get(@RequestParam("location") location: String, response: HttpServletResponse): Response<Unit> {
        val file = this.fileService.getFile(location) ?: return Response.fail("下载文件", message = "文件不存在")
        BufferedInputStream(FileInputStream(file)).use {
            val sos = response.outputStream
            response.contentType = "application/octet-stream"
            response.addHeader("Content-Disposition", "attachment;filename=${file.name}")
            it.transferTo(sos)
        }
        return Response.succ("下载文件")
    }

    /**
     * 删除文件
     */
    @ResponseBody
    @DeleteMapping("/{location}")
    fun delete(@PathVariable("location") location: String): Response<Unit> {
        return try {
            this.fileService.delete(location)
            Response.succ("删除文件")
        } catch (ex: Exception) {
            Response.fail("删除文件", exception = ex)
        }
    }
}