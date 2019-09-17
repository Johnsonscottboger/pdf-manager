package com.pdf.manager.pdfmanagerweb.service

/**
 * 提供对实体的操方法
 */
interface IBaseService<TEntity : Any> {
    /**
     * 添加 [TEntity] 记录
     * @param entity 指定添加的 [TEntity] 实例
     */
    fun add(entity: TEntity)

    /**
     * 添加 [TEntity] 记录
     * @param entities 指定添加的 [TEntity] 集合
     */
    fun addRange(entities: List<TEntity>)

    /**
     * 删除 [TEntity] 记录
     * @param entity 删除的 [TEntity] 实例
     */
    fun delete(entity: TEntity)

    /**
     * 修改 [TEntity] 记录
     * @param entity 修改的 [TEntity] 实例
     */
    fun update(entity: TEntity)

    /**
     * 添加或修改 [TEntity] 记录
     * @param entity 添加或修改的 [TEntity] 实例
     */
    fun addOrUpdate(entity: TEntity)

    /**
     * 获取所有 [TEntity] 记录
     */
    fun getAll(): List<TEntity>

    /**
     * 获取指定 [id] 的 [TEntity] 记录
     * @param id 指定的 [TEntity] 主键
     * @return [TEntity] 记录
     */
    fun getById(id: String): TEntity?

    /**
     * 获取指定 [name] 的 [TEntity] 记录
     * @param name 指定的 [TEntity] 名称
     * @return [TEntity] 记录
     */
    fun getByName(name: String): List<TEntity>
}