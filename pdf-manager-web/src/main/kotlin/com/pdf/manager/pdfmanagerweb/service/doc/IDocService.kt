package com.pdf.manager.pdfmanagerweb.service.doc

import com.pdf.manager.pdfmanagerweb.domain.dataobject.Doc
import com.pdf.manager.pdfmanagerweb.service.IBaseService

/**
 * 提供对 [Doc] 实体的操作服务
 */
interface IDocService : IBaseService<Doc> {

    /**
     * 获取指定 [pdfId] 的 [Doc] 记录
     * @param pdfId 指定的 [Pdf] 主键
     * @return [Doc] 记录
     */
    fun getByPdfId(pdfId: String): List<Doc>
}