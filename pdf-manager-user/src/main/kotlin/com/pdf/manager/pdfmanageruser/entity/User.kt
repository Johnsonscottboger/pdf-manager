package com.pdf.manager.pdfmanageruser.entity

/**
 * 用户实体
 */
data class User(
        /**
         * 主键
         */
        val id : String,

        /**
         * 姓名
         */
        val name: String,

        /**
         * 密码
         */
        val password : String,

        /**
         * 手机号
         */
        val mobilePhoneNumber: String
)