package com.pdf.manager.pdfmanagerweb.service.doc.impl

import com.pdf.manager.pdfmanagerweb.domain.dto.ConverterType
import com.pdf.manager.pdfmanagerweb.domain.dto.Response
import com.pdf.manager.pdfmanagerweb.service.cnv.IMSWordConvert
import com.pdf.manager.pdfmanagerweb.service.doc.IDocConvertFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DefaultDocConvertFactoryImpl : IDocConvertFactory {

    @Autowired
    private lateinit var mswordConvert: IMSWordConvert

    /**
     * 将指定的 PDF 转换为 Doc
     * @param converterType 指定的转换方式
     * @param pdfId 指定的 PDF
     * @param fileName 指定的 PDF 文件名
     * @param location 指定的文件路径
     */
    override fun convertToDoc(converterType: ConverterType, pdfId: String, fileName: String, location: String) {
        when (converterType) {
            ConverterType.MS_WORD -> this.mswordConvert.convert(pdfId, fileName, location)
        }
    }
}