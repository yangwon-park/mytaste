package com.turnover.my.taste.unit.service.member

import com.turnover.my.taste.app.repository.member.MemberRepository
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class MemberServiceTest {

    @Mock
    lateinit var memberRepository: MemberRepository
}