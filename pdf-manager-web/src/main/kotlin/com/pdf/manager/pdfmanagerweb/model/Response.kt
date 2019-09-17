package com.pdf.manager.pdfmanagerweb.model

/**
 * 响应结果
 */
class Response() : HashMap<String, Any>() {
    init {
        this[KEY_OPER] = DEFAULT_OPER_VALE
        this[KEY_SUCC] = true
        this[KEY_CODE] = DEFAULT_SUCC_CODE
        this[KEY_MESG] = DEFAULT_SUCC_CODE
    }

    constructor(oper: String) : this() {
        this[KEY_OPER] = oper
    }

    constructor(operation: String, success: Boolean, code: Int, mesg: String, data: Any?) : this() {
        this[KEY_OPER] = operation
        this[KEY_SUCC] = success
        this[KEY_CODE] = code
        this[KEY_MESG] = mesg
        if (data != null)
            this[KEY_DATA] = data
    }

    /**
     * 设置操作名称
     */
    fun oper(operation: String): Response {
        this[KEY_OPER] = operation
        return this
    }

    /**
     * 设置操作结果是否成功标记
     */
    fun succ(success: Boolean): Response {
        this[KEY_SUCC] = success
        return this
    }

    /**
     * 设置操作结果代码
     */
    fun code(code: Int): Response {
        this[KEY_CODE] = code
        return this
    }

    /**
     * 设置操作结果信息
     */
    fun msg(message: String): Response {
        this[KEY_MESG] = message
        return this
    }

    /**
     * 设置操作返回的数据
     */
    fun data(data: Any): Response {
        this[KEY_DATA] = data
        return this
    }

    /**
     * 设置操作返回的数据, 数据使用自定义的键
     */
    fun data(key: String, data: Any): Response {
        this[key] = data
        return this
    }

    companion object {
        const val KEY_OPER = "oper"
        const val KEY_SUCC = "succ"
        const val KEY_CODE = "code"
        const val KEY_MESG = "mesg"
        const val KEY_DATA = "data"

        const val DEFAULT_OPER_VALE = "default"
        const val DEFAULT_SUCC_CODE = 200
        const val DEFAULT_FAIL_CODE = 500
        const val DEFAULT_SUCC_MESG = "ok"
        const val DEFAULT_FAIL_MESG = "fail"

        fun succ(operation: String = DEFAULT_OPER_VALE,
                 code: Int = DEFAULT_SUCC_CODE,
                 message: String = DEFAULT_SUCC_MESG,
                 data: Any? = null): Response {
            return Response(operation, true, code, message, data)
        }

        fun fail(operation: String = DEFAULT_OPER_VALE,
                 code: Int = DEFAULT_FAIL_CODE,
                 message: String = DEFAULT_FAIL_MESG,
                 data: Any? = null): Response {
            return Response(operation, false, code, message, data)
        }

        fun result(operation: String,
                   success: Boolean,
                   data: Any? = null): Response {
            return Response(operation,
                    success,
                    if (success) DEFAULT_SUCC_CODE else DEFAULT_FAIL_CODE,
                    if (success) DEFAULT_SUCC_MESG else DEFAULT_FAIL_MESG,
                    data)
        }
    }
}