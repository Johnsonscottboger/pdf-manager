package com.pdf.manager.pdfmanagerexternalmsword.domain.dao

import com.pdf.manager.pdfmanagerexternalmsword.domain.dataobject.Doc

/**
 * 提供对 [Doc] 实体的操作方法
 */
interface IDoc : IDao<Doc> {

    /**
     * 获取指定 [pdfId] 的 [Doc] 记录
     * @param pdfId 指定的 [Pdf] 主键
     * @return [Doc] 记录
     */
    fun getByPdfId(pdfId: String): List<Doc>
}