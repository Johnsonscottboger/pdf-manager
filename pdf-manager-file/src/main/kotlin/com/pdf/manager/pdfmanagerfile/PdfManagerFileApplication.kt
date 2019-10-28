package com.pdf.manager.pdfmanagerfile

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.netflix.eureka.EnableEurekaClient

@SpringBootApplication
@EnableEurekaClient
class PdfManagerFileApplication

fun main(args: Array<String>) {
    runApplication<PdfManagerFileApplication>(*args)
}
