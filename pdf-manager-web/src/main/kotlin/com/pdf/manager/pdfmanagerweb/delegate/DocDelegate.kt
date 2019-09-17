package com.pdf.manager.pdfmanagerweb.delegate

import com.pdf.manager.pdfmanagerweb.dao.IDoc
import com.pdf.manager.pdfmanagerweb.entity.Doc
import com.pdf.manager.pdfmanagerweb.entity.Pdf
import javax.annotation.Resource
import kotlin.reflect.KProperty

/**
 * DOC 委托
 */
class DocDelegate {
    @Resource
    private lateinit var _docDao: IDoc

    operator fun getValue(pdf: Pdf, property: KProperty<*>): Iterable<Doc> {
        return this._docDao.getByPdfId(pdf.id)
    }
}