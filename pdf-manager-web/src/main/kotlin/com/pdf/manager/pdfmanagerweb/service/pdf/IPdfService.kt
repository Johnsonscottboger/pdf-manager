package com.pdf.manager.pdfmanagerweb.service.pdf

import com.pdf.manager.pdfmanagerweb.domain.dataobject.Pdf
import com.pdf.manager.pdfmanagerweb.service.IBaseService

/**
 * 提供对 [Pdf] 实体的操作服务
 */
interface IPdfService : IBaseService<Pdf> {

    /**
     * 获取指定 [userId] 的 [Pdf] 记录
     * @param userId 指定的 [User] 主键
     * @return [Pdf] 记录
     */
    fun getByUserId(userId: String): List<Pdf>
}