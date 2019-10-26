package com.pdf.manager.pdfmanagerweb.domain.dto

import org.slf4j.LoggerFactory

/**
 * 响应结果
 */
class Response<T : Any>() : HashMap<String, Any>() {
    init {
        this[KEY_OPER] = DEFAULT_OPER_VALE
        this[KEY_SUCC] = true
        this[KEY_CODE] = DEFAULT_SUCC_CODE
        this[KEY_MESG] = DEFAULT_SUCC_CODE
    }

    constructor(oper: String) : this() {
        this[KEY_OPER] = oper
    }

    constructor(operation: String, success: Boolean, code: Int, mesg: String, data: T?) : this() {
        this[KEY_OPER] = operation
        this[KEY_SUCC] = success
        this[KEY_CODE] = code
        this[KEY_MESG] = mesg
        if (data != null)
            this[KEY_DATA] = data
    }

    constructor(operation: String, success: Boolean, code: Int, mesg: String, data: T?, exception: Exception?)
            : this(operation, success, code, mesg, data) {
        if (exception != null)
            this[KEY_EXPT] = exception
    }

    /**
     * 设置操作名称
     */
    fun oper(operation: String): Response<T> {
        this[KEY_OPER] = operation
        return this
    }

    /**
     * 设置操作结果是否成功标记
     */
    fun succ(success: Boolean): Response<T> {
        this[KEY_SUCC] = success
        return this
    }

    /**
     * 设置操作结果代码
     */
    fun code(code: Int): Response<T> {
        this[KEY_CODE] = code
        return this
    }

    /**
     * 设置操作结果信息
     */
    fun msg(message: String): Response<T> {
        this[KEY_MESG] = message
        return this
    }

    /**
     * 设置操作返回的数据
     */
    fun data(data: T): Response<T> {
        this[KEY_DATA] = data
        return this
    }

    /**
     * 设置操作返回的数据, 数据使用自定义的键
     */
    fun data(key: String, data: T): Response<T> {
        this[key] = data
        return this
    }

    /**
     * 设置操作异常信息
     */
    fun exception(exception: Exception): Response<T> {
        this[KEY_EXPT] = exception
        return this
    }

    companion object {
        const val KEY_OPER = "oper"
        const val KEY_SUCC = "succ"
        const val KEY_CODE = "code"
        const val KEY_MESG = "mesg"
        const val KEY_DATA = "data"
        const val KEY_EXPT = "expt"

        const val DEFAULT_OPER_VALE = "default"
        const val DEFAULT_SUCC_CODE = 200
        const val DEFAULT_FAIL_CODE = 500
        const val DEFAULT_SUCC_MESG = "ok"
        const val DEFAULT_FAIL_MESG = "fail"

        private val log = LoggerFactory.getLogger(Response::class.java)

        fun <T : Any> succ(operation: String = DEFAULT_OPER_VALE,
                           code: Int = DEFAULT_SUCC_CODE,
                           message: String = DEFAULT_SUCC_MESG,
                           data: T? = null): Response<T> {
            log.info(operation)
            return Response(operation, true, code, message, data)
        }

        fun <T : Any> fail(operation: String = DEFAULT_OPER_VALE,
                           code: Int = DEFAULT_FAIL_CODE,
                           message: String = DEFAULT_FAIL_MESG,
                           data: T? = null,
                           exception: Exception? = null): Response<T> {
            log.error(operation, data, exception)
            return Response(operation, false, code, message, data, exception)
        }

        fun <T : Any> result(operation: String,
                             success: Boolean,
                             data: T? = null,
                             exception: Exception?): Response<T> {
            if(success)
                log.info(operation)
            else
                log.error(operation, data, exception)
            return Response(operation,
                    success,
                    if (success) DEFAULT_SUCC_CODE else DEFAULT_FAIL_CODE,
                    if (success) DEFAULT_SUCC_MESG else DEFAULT_FAIL_MESG,
                    data,
                    exception)
        }
    }
}