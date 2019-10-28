package com.pdf.manager.pdfmanagerfile.service.dto

/**
 * 添加文件结果
 */
data class FileResult(

        /**
         * 文件的 MD5 值
         */
        val md5: String,

        /**
         * 文件的路径
         */
        val location: String
)