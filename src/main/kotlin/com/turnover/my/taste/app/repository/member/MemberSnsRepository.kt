package com.turnover.my.taste.app.repository.member

import com.turnover.my.taste.app.domain.member.MemberSns
import org.springframework.data.jpa.repository.JpaRepository

interface MemberSnsRepository: JpaRepository<MemberSns, Long> {
}