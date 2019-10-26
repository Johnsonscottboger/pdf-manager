package com.pdf.manager.pdfmanagerweb.service.doc.event

import com.pdf.manager.pdfmanagerweb.domain.dataobject.Doc
import com.pdf.manager.pdfmanagerweb.service.BaseEvent

/**
 * 获取 PDF 的 Word 文档
 */
data class GetWordEvent(
        /**
         * PDF 文件
         */
        val pdfId: String,

        /**
         * 获取操作
         */
        override val operation: String = "获取 PDF 的 Word 文档"
) : BaseEvent<Doc>()