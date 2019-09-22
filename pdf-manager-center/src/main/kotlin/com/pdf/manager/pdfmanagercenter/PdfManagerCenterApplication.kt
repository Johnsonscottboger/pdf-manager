package com.pdf.manager.pdfmanagercenter

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer

/**
 * 服务注册发现中心
 */
@SpringBootApplication
@EnableEurekaServer
class PdfManagerCenterApplication

fun main(args: Array<String>) {
    runApplication<PdfManagerCenterApplication>(*args)
}
