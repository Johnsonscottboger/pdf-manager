package com.pdf.manager.pdfmanagerweb

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

/**
 * Web 服务
 */
@SpringBootApplication
@EnableDiscoveryClient
class PdfManagerWebApplication

fun main(args: Array<String>) {
    runApplication<PdfManagerWebApplication>(*args)
}
