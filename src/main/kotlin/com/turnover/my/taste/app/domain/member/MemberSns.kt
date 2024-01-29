package com.turnover.my.taste.app.domain.member

import com.turnover.my.taste.app.domain.member.enums.SnsKind
import jakarta.persistence.*

@Entity
@Table(name = "member_sns", schema = "app")
class MemberSns(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_sns_id")
    val id: Long? = null,

    @Column(name = "sns_id", length = 100, nullable = false)
    val snsId: String,

    @Column(name = "kind", length = 10, nullable = false)
    val kind: SnsKind,
) {

}