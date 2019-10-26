package com.pdf.manager.pdfmanagerweb.service.pdf.event

import com.pdf.manager.pdfmanagerweb.domain.dataobject.Pdf
import com.pdf.manager.pdfmanagerweb.service.BaseEvent
import org.springframework.web.multipart.MultipartFile
import java.io.InputStream

/**
 * 添加 PDF 事件
 */
data class AddPdfEvent(

        /**
         * 用户主键
         */
        val userId: String,

        /**
         * 文件名
         */
        val fileName: String,

        /**
         * 文件输入流
         */
        val inputStream: MultipartFile,

        /**
         * 操作
         */
        override val operation: String = "添加PDF文件"
) : BaseEvent<Unit>()