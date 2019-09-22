package com.pdf.manager.pdfmanagerweb.domain.dataobject

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.pdf.manager.pdfmanagerweb.domain.delegate.DocDelegate
import java.time.LocalDateTime

/**
 * PDF 实体
 */
@JsonIgnoreProperties("docs")
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