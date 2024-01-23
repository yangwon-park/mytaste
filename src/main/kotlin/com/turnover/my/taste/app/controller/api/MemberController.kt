package com.turnover.my.taste.app.controller.api

import com.turnover.my.taste.app.service.member.MemberService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/member")
class MemberController(
    val memberService: MemberService,
) {

    @GetMapping
    fun getMember() {

    }
}