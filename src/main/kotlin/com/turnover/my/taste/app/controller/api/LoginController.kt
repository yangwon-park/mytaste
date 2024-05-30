package com.turnover.my.taste.app.controller.api

import com.turnover.my.taste.app.domain.login.dto.LoginDTO
import com.turnover.my.taste.app.service.login.LoginService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/login")
class LoginController(
    private val loginService: LoginService,
) {

    @PostMapping("/sns")
    fun proceedLoginLogin(
        @RequestBody snsInfo: LoginDTO.SnsInfo,
        @RequestHeader(required = false) deviceInfo: String?,
    ) {
        loginService.proceedSnsLogin(snsInfo, deviceInfo)
    }
}