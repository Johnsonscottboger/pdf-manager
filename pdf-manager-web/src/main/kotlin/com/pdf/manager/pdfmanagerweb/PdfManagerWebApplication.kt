package com.pdf.manager.pdfmanagerweb

import com.pdf.manager.pdfmanagerweb.infrastructure.event.eventhandler.EventHandler
import com.pdf.manager.pdfmanagerweb.infrastructure.event.eventhandler.IEventHandler
import org.mybatis.spring.annotation.MapperScan
import org.springframework.beans.factory.getBeansWithAnnotation
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.openfeign.EnableFeignClients

/**
 * Web 服务
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan(basePackages = ["com.pdf.manager.pdfmanagerweb.domain.dao"])
class PdfManagerWebApplication

fun main(args: Array<String>) {
    runApplication<PdfManagerWebApplication>(*args)
}
