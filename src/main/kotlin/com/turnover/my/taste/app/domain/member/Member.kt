package com.turnover.my.taste.app.domain.member

import com.turnover.my.taste.app.domain.common.BaseTimeEntity
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "member", schema = "app")
class Member(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    val id: Long? = null,

    @Column(name = "sns_id")
    val snsId: String,

    @Column(name = "join_route")
    val joinRoute: String,

    @Column(name = "nickname")
    val nickname: String,

    @Column(name = "duplicate_info")
    val duplicateInfo: String,

    @Column(name = "last_login_time")
    val lastLoginTime: LocalDateTime,
) : BaseTimeEntity() {


}