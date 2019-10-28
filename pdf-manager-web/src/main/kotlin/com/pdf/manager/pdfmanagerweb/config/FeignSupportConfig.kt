package com.pdf.manager.pdfmanagerweb.config

import feign.codec.Encoder
import feign.form.spring.SpringFormEncoder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FeignSupportConfig {

    @Bean
    fun feignFormEncoder(): Encoder {
        return SpringFormEncoder()
    }
}