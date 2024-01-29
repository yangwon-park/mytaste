package com.turnover.my.taste.app.domain.member

import com.turnover.my.taste.app.domain.member.enums.Gender
import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "member_personal", schema = "app")
class MemberPersonal(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_personal_id")
    val id: Long? = null,

    @Column(name = "phone_number", length = 20, nullable = false)
    val phoneNumber: String,

    @Column(name = "birthdate", nullable = false)
    val birthDate: LocalDate,

    @Column(name = "gender", length = 10, nullable = false)
    val gender: Gender,
) {

    @OneToOne(mappedBy = "memberPersonal")
    var member: Member? = null
}