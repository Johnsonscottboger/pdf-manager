package com.pdf.manager.pdfmanagerweb.service.pdf.event

import com.pdf.manager.pdfmanagerweb.service.BaseEvent

/**
 * 获取指定用户的所有 PDF 文件
 */
class GetUserAllPdfEvent(
        /**
         * 指定的用户标识
         */
        val userId: String,

        /**
         * 获取操作
         */
        override val operation: String = "获取用户所有 PDF 文件"
) : BaseEvent()