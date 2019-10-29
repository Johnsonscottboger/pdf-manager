package com.pdf.manager.pdfmanagerweb.domain.dto

/**
 * PDF 状态
 */
enum class PdfStatus(name: String) {
    /**
     * 正常
     */
    NORMAL("正常"),

    /**
     * 正在转换
     */
    CONVERTING("正在转换"),

    /**
     * 转换成功
     */
    SUCCESS("转换成功"),

    /**
     * 转换失败
     */
    FAILED("转换失败")
}