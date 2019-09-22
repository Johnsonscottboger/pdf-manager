package com.pdf.manager.pdfmanagerweb.web.controller

import com.pdf.manager.pdfmanagerweb.service.pdf.event.GetUserAllPdfEvent
import com.pdf.manager.pdfmanagerweb.infrastructure.event.eventbus.IEventBus
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class HomeController(val eventBus: IEventBus) {

    @ResponseBody
    @GetMapping("/")
    fun index() = "Welcome Web Application"
}