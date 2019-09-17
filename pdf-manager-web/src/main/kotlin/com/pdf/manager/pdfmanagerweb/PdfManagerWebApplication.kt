package com.pdf.manager.pdfmanagerweb

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
/**
 * Web 服务
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = ["com.pdf.manager.pdfmanagerweb.dao"])
class PdfManagerWebApplication

fun main(args: Array<String>) {
    runApplication<PdfManagerWebApplication>(*args)
}
