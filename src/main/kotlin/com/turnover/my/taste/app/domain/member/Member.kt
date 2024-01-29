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

    @Column(name = "status")
    val status: MemberStatus,

    @Column(name = "nickname")
    val nickname: String,

    @Column(name = "intro")
    val intro: String?,

    @Column(name ="profile_image_url")
    val profileImageUrl: String?,

) : BaseTimeEntity() {

}