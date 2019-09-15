package com.pdf.manager.pafmanagercenter

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer

/**
 * 服务注册发现中心
 */
@SpringBootApplication
@EnableEurekaServer
class PafManagerCenterApplication

fun main(args: Array<String>) {
    runApplication<PafManagerCenterApplication>(*args)
}
