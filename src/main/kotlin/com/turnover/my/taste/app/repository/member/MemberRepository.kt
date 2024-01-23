package com.turnover.my.taste.app.repository.member

import com.turnover.my.taste.app.domain.member.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long> {
}