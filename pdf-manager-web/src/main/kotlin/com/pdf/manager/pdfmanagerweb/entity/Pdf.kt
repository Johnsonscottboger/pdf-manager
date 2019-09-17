package com.pdf.manager.pdfmanagerweb.entity

import com.pdf.manager.pdfmanagerweb.delegate.DocDelegate
import java.time.LocalDateTime

/**
 * PDF 实体
 */
data class Pdf(
        /**
         * 主键
         */
        var id: String,

        /**
         * MD5
         */
        var md5: String,

        /**
         * 用户主键
         */
        var userId: String,

        /**
         * 名称
         */
        var name: String,

        /**
         * 路径
         */
        var location: String,

        /**
         * 状态标志
         */
        var status: Int,

        /**
         * PDF 转换结果
         */
        var result: String,

        /**
         * 创建时间
         */
        var createDateTime: LocalDateTime
) {
    val docs: Iterable<Doc> by DocDelegate()
}