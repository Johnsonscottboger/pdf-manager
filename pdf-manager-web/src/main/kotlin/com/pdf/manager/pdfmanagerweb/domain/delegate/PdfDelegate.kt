package com.pdf.manager.pdfmanagerweb.domain.delegate

import com.pdf.manager.pdfmanagerweb.domain.dao.IPdf
import com.pdf.manager.pdfmanagerweb.domain.dataobject.Pdf
import com.pdf.manager.pdfmanagerweb.domain.dataobject.User
import javax.annotation.Resource
import kotlin.reflect.KProperty

/**
 * PDF 委托
 */
class PdfDelegate {
    @Resource
    private lateinit var _pdfDao: IPdf

    private var _valueLazy : Lazy<Iterable<Pdf>>? = null

    operator fun getValue(user: User, property: KProperty<*>): Iterable<Pdf> {
        return if(this._valueLazy != null && this._valueLazy!!.isInitialized())
            this._valueLazy!!.value
        else {
            val value = this._pdfDao.getByUserId(user.id)
            this._valueLazy = lazyOf(value)
            value
        }
    }
}