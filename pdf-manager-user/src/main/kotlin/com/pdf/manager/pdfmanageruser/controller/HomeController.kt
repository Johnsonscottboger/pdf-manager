package com.pdf.manager.pdfmanageruser.controller

import com.pdf.manager.pdfmanageruser.annotation.AllowAnonymous
import com.pdf.manager.pdfmanageruser.entity.User
import com.pdf.manager.pdfmanageruser.model.Response
import com.pdf.manager.pdfmanageruser.service.IUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseBody
import javax.servlet.http.HttpServletRequest

/**
 * 主页控制器
 */
@Controller
@AllowAnonymous
class HomeController {
    @Autowired
    private lateinit var _userService: IUserService

    /**
     * 首页
     */
    @GetMapping("/")
    @ResponseBody
    fun index(): String {
        return "Welcome User application."
    }

    /**
     * 登录
     */
    @ResponseBody
    @PostMapping("/login")
    fun login(request: HttpServletRequest, @RequestBody user: User): Response {
        TODO("完成登录逻辑")
    }

    /**
     * 注册
     */
    @ResponseBody
    @PostMapping("/register")
    fun register(request: HttpServletRequest, @RequestBody user: User): Response {
        TODO("完成注册逻辑")
    }
}