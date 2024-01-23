package com.turnover.my.taste.app.controller.api

import com.turnover.my.taste.app.domain.member.dto.MemberDTO
import com.turnover.my.taste.app.service.member.MemberService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/members")
class MemberController(
    val memberService: MemberService,
) {

    @GetMapping
    fun getMember() {

    }

    @PostMapping("/join")
    fun joinMember(@RequestBody request: MemberDTO.JoinRequest): Long? {
        return memberService.joinMember(request)
    }
}