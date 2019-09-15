package com.pdf.manager.pdfmanageruser

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * User 服务
 */
@SpringBootApplication
@MapperScan(basePackages = ["com.pdf.manager.pdfmanageruser.dao"])
class PdfManagerUserApplication

fun main(args: Array<String>) {
    runApplication<PdfManagerUserApplication>(*args)
}
