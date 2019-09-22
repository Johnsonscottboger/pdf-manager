package com.pdf.manager.pdfmanagerweb.service.doc.impl

import com.pdf.manager.pdfmanagerweb.domain.dao.IDoc
import com.pdf.manager.pdfmanagerweb.domain.dataobject.Doc
import com.pdf.manager.pdfmanagerweb.service.doc.IDocService
import org.springframework.stereotype.Service
import javax.annotation.Resource

/**
 * [Doc] 操作服务默认实现
 */
@Service
class DefaultDocServiceImpl() : IDocService {

    @Resource
    private lateinit var _dao: IDoc

    /**
     * 获取指定 [pdfId] 的 [Doc] 记录
     * @param pdfId 指定的 [Pdf] 主键
     * @return [Doc] 记录
     */
    override fun getByPdfId(pdfId: String): List<Doc> {
        return this._dao.getByPdfId(pdfId)
    }

    /**
     * 添加 [Doc] 记录
     * @param entity 指定添加的 [Doc] 实例
     */
    override fun add(entity: Doc) {
        return this._dao.add(entity)
    }

    /**
     * 添加 [Doc] 记录
     * @param entities 指定添加的 [Doc] 集合
     */
    override fun addRange(entities: List<Doc>) {
        return this._dao.addRange(entities)
    }

    /**
     * 删除 [Doc] 记录
     * @param entity 删除的 [Doc] 实例
     */
    override fun delete(entity: Doc) {
        return this._dao.delete(entity)
    }

    /**
     * 修改 [Doc] 记录
     * @param entity 修改的 [Doc] 实例
     */
    override fun update(entity: Doc) {
        return this._dao.update(entity)
    }

    /**
     * 添加或修改 [Doc] 记录
     * @param entity 添加或修改的 [Doc] 实例
     */
    override fun addOrUpdate(entity: Doc) {
        return this._dao.addOrUpdate(entity)
    }

    /**
     * 获取所有 [Doc] 记录
     */
    override fun getAll(): List<Doc> {
        return this._dao.getAll()
    }

    /**
     * 获取指定 [id] 的 [Doc] 记录
     * @param id 指定的 [Doc] 主键
     * @return [Doc] 记录
     */
    override fun getById(id: String): Doc? {
        return this._dao.getById(id)
    }

    /**
     * 获取指定 [name] 的 [Doc] 记录
     * @param name 指定的 [Doc] 名称
     * @return [Doc] 记录
     */
    override fun getByName(name: String): List<Doc> {
        return this._dao.getByName(name)
    }
}