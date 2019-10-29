package com.pdf.manager.pdfmanagerweb.service.doc

import com.pdf.manager.pdfmanagerweb.domain.dto.ConverterType
import com.pdf.manager.pdfmanagerweb.domain.dto.Response

/**
 * 文档转换工厂
 */
interface IDocConvertFactory {

    /**
     * 将指定的 PDF 转换为 Doc
     * @param converterType 指定的转换方式
     * @param pdfId 指定的 PDF
     * @param fileName 指定的 PDF 文件名
     * @param location 指定的文件路径
     */
    fun convertToDoc(converterType: ConverterType, pdfId: String, fileName: String, location: String)
}