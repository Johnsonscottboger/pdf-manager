package com.pdf.manager.pdfmanagerweb.delegate

import com.pdf.manager.pdfmanagerweb.dao.IPdf
import com.pdf.manager.pdfmanagerweb.entity.Pdf
import com.pdf.manager.pdfmanagerweb.entity.User
import javax.annotation.Resource
import kotlin.reflect.KProperty

/**
 * PDF 委托
 */
class PdfDelegate {
    @Resource
    private lateinit var _pdfDao: IPdf

    operator fun getValue(user: User, property: KProperty<*>): Iterable<Pdf> {
        return this._pdfDao.getByUserId(user.id)
    }
}