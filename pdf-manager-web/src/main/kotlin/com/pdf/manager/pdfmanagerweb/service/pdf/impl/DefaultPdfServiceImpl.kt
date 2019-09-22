package com.pdf.manager.pdfmanagerweb.service.pdf.impl

import com.pdf.manager.pdfmanagerweb.domain.dao.IPdf
import com.pdf.manager.pdfmanagerweb.domain.dataobject.Pdf
import com.pdf.manager.pdfmanagerweb.service.pdf.IPdfService
import org.springframework.stereotype.Service
import javax.annotation.Resource

/**
 * [Pdf] 操作服务默认实现
 */
@Service
class DefaultPdfServiceImpl : IPdfService {

    @Resource
    private lateinit var _dao: IPdf

    /**
     * 获取指定 [userId] 的 [Pdf] 记录
     * @param userId 指定的 [User] 主键
     * @return [Pdf] 记录
     */
    override fun getByUserId(userId: String): List<Pdf> {
        return this._dao.getByUserId(userId)
    }

    /**
     * 添加 [Pdf] 记录
     * @param entity 指定添加的 [Pdf] 实例
     */
    override fun add(entity: Pdf) {
        return this._dao.add(entity)
    }

    /**
     * 添加 [Pdf] 记录
     * @param entities 指定添加的 [Pdf] 集合
     */
    override fun addRange(entities: List<Pdf>) {
        this._dao.addRange(entities)
    }

    /**
     * 删除 [Pdf] 记录
     * @param entity 删除的 [Pdf] 实例
     */
    override fun delete(entity: Pdf) {
        this._dao.delete(entity)
    }

    /**
     * 修改 [Pdf] 记录
     * @param entity 修改的 [Pdf] 实例
     */
    override fun update(entity: Pdf) {
        this._dao.update(entity)
    }

    /**
     * 添加或修改 [Pdf] 记录
     * @param entity 添加或修改的 [Pdf] 实例
     */
    override fun addOrUpdate(entity: Pdf) {
        return this._dao.addOrUpdate(entity)
    }

    /**
     * 获取所有 [Pdf] 记录
     */
    override fun getAll(): List<Pdf> {
        return this._dao.getAll()
    }

    /**
     * 获取指定 [id] 的 [Pdf] 记录
     * @param id 指定的 [Pdf] 主键
     * @return [Pdf] 记录
     */
    override fun getById(id: String): Pdf? {
        return this._dao.getById(id)
    }

    /**
     * 获取指定 [name] 的 [Pdf] 记录
     * @param name 指定的 [Pdf] 名称
     * @return [Pdf] 记录
     */
    override fun getByName(name: String): List<Pdf> {
        return this._dao.getByName(name)
    }
}