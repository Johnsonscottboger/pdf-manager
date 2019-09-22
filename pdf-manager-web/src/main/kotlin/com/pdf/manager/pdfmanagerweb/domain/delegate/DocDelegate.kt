package com.pdf.manager.pdfmanagerweb.domain.delegate

import com.pdf.manager.pdfmanagerweb.domain.dao.IDoc
import com.pdf.manager.pdfmanagerweb.domain.dataobject.Doc
import com.pdf.manager.pdfmanagerweb.domain.dataobject.Pdf
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.annotation.Resource
import kotlin.reflect.KProperty

/**
 * DOC 委托
 */
@Component
class DocDelegate() {
    @Resource
    private lateinit var _docDao: IDoc

    private var _valueLazy : Lazy<Iterable<Doc>>? = null

    operator fun getValue(pdf: Pdf, property: KProperty<*>): Iterable<Doc> {
        return if(this._valueLazy != null && this._valueLazy!!.isInitialized())
            this._valueLazy!!.value
        else {
            val value = this._docDao.getByPdfId(pdf.id)
            this._valueLazy = lazyOf(value)
            value
        }
    }
}