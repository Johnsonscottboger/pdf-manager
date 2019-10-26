package com.pdf.manager.pdfmanagerweb.service.pdf.event

import com.pdf.manager.pdfmanagerweb.service.BaseEvent

/**
 * 将 PDF 转换为 Word 事件
 */
data class ConvertToWordEvent(
        /**
         * 用户主键
         */
        val userId: String,

        /**
         * PDF 文件
         */
        val pdfId: String,

        /**
         * 获取操作
         */
        override val operation: String = "将 PDF 转换为 Word"
) : BaseEvent<Unit>()