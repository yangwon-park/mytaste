package com.turnover.my.taste.app.repository.member

import com.turnover.my.taste.app.domain.member.MemberPersonal
import org.springframework.data.jpa.repository.JpaRepository

interface MemberPersonalRepository: JpaRepository<MemberPersonal, Long> {
}