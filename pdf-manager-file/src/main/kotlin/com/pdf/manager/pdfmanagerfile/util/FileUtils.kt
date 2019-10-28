package com.pdf.manager.pdfmanagerfile.util

import org.springframework.util.DigestUtils
import java.io.File
import java.io.FileInputStream
import java.io.InputStream


/**
 * 文件工具类
 */
class FileUtils {

    companion object {

        /**
         * 获取指定文件的 MD5 值
         */
        fun getMD5(fileName: String): String {
            return getMD5(File(fileName))
        }

        /**
         * 获取文件的 MD5 值
         */
        fun getMD5(file: File): String {
            return getMD5(FileInputStream(file))
        }

        /**
         * 获取文件的 MD5 值
         */
        fun getMD5(fileInputStream: InputStream): String {
            return DigestUtils.md5DigestAsHex(fileInputStream)
        }
    }
}