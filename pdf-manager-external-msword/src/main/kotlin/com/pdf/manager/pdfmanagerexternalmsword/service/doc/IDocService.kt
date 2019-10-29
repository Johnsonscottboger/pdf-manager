package com.pdf.manager.pdfmanagerexternalmsword.service.doc

import com.pdf.manager.pdfmanagerexternalmsword.domain.dataobject.Doc
import com.pdf.manager.pdfmanagerexternalmsword.service.IBaseService
import org.springframework.web.multipart.MultipartFile


/**
 * 提供对 [Doc] 实体的操作服务
 */
interface IDocService : IBaseService<Doc> {

    /**
     * 获取指定 [pdfId] 的 [Doc] 记录
     * @param pdfId 指定的 PDF
     * @return [Doc] 记录
     */
    fun getByPdfId(pdfId: String): List<Doc>

    /**
     * 将指定的 PDF 文件转换为 Word
     * @param pdfId 指定的 PDF
     * @param fileName 指定的 PDF 文件名
     * @param location 指定的文件位置
     */
    fun convert(pdfId: String, fileName: String, location: String)
}