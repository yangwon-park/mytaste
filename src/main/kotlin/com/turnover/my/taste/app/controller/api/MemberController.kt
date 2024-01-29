package com.turnover.my.taste.app.controller.api

import com.turnover.my.taste.app.domain.member.dto.MemberDTO
import com.turnover.my.taste.app.service.member.MemberService
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

private val logger = KotlinLogging.logger {}

@RestController
@RequestMapping("/api/members")
class MemberController(
    val memberService: MemberService,
) {

    @GetMapping
    fun getMember() {

    }

    @PostMapping("/signup")
    fun signUpMember(@RequestBody request: MemberDTO.SignUpRequest): Long? {
        return memberService.signUpMember(request)
    }
}