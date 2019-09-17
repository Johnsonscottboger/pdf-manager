package com.pdf.manager.pdfmanageruser.dao

import com.pdf.manager.pdfmanageruser.entity.User

/**
 * 提供 [User] 实体的操作方法
 */
interface IUser {

    /**
     * 添加 [User] 记录
     * @param user 指定添加的 [user] 实例
     */
    fun add(user: User)

    /**
     * 添加 [User] 记录
     * @param users 添加的 [User] 集合
     */
    fun addRange(users: List<User>)

    /**
     * 删除 [User] 记录
     * @param user 删除的 [User] 实例
     */
    fun delete(user: User)

    /**
     * 修改 [User] 记录
     * @param user 修改的 [User] 实例
     */
    fun update(user: User)

    /**
     * 添加或修改 [User] 记录
     * @param user 添加或修改的 [User] 实例
     */
    fun addOrUpdate(user: User)

    /**
     * 获取所有 [User] 记录
     * @return 所有 [User] 记录
     */
    fun getAll(): List<User>

    /**
     * 获取指定 [id] 的 [User] 记录
     * @param id 指定的 [User] 主键
     * @return [User] 记录
     */
    fun getById(id: String): User?

    /**
     * 获取指定 [name] 的 [User] 记录
     * @param name 指定的 [User] 名称
     * @return [User] 记录
     */
    fun getByName(name: String): List<User>
}