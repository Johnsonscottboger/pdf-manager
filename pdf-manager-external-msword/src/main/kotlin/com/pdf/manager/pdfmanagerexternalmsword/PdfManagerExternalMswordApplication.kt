package com.pdf.manager.pdfmanagerexternalmsword

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan(basePackages = ["com.pdf.manager.pdfmanagerexternalmsword.domain.dao"])
class PdfManagerExternalMswordApplication

fun main(args: Array<String>) {
    runApplication<PdfManagerExternalMswordApplication>(*args)
}
