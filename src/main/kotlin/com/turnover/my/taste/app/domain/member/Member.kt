package com.turnover.my.taste.app.domain.member

import com.turnover.my.taste.app.domain.common.BaseTimeEntity
import com.turnover.my.taste.app.domain.member.enums.MemberStatus
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "member", schema = "app")
class Member(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    val id: Long? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 10)
    val status: MemberStatus, // default: ACTIVE

    @Column(name = "nickname", length = 30)
    val nickname: String,

    @Column(name = "intro", length = 50)
    val intro: String?,

    @Column(name = "profile_image_url", length = 200)
    val profileImageUrl: String?
) : BaseTimeEntity() {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_sns_id")
    var memberSns: MemberSns? = null

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_personal_id")
    var memberPersonal: MemberPersonal? = null

    fun linkPersonalAndSns(memberSns: MemberSns, memberPersonal: MemberPersonal) {
        this.memberSns = memberSns
        this.memberPersonal = memberPersonal
    }

}