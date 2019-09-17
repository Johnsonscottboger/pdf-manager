package com.pdf.manager.pdfmanagerweb.entity

import java.time.LocalDateTime

/**
 * Doc 实体
 */
data class Doc(
        /**
         * 主键
         */
        var id: String,

        /**
         * MD5
         */
        var md5: String,

        /**
         * Pdf 主键
         */
        var pdfId: String,

        /**
         * 名称
         */
        var name: String,

        /**
         * 路径
         */
        var location: String,

        /**
         * 生成方式
         */
        var generatedType: String,

        /**
         * 平分
         */
        var score: Double,

        /**
         * 生成时间
         */
        var createDateTime: LocalDateTime,

        /**
         * 备注
         */
        var comments: String
)