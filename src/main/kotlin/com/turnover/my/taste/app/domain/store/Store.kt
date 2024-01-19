package com.turnover.my.taste.app.domain.store

import com.turnover.my.taste.app.domain.embedded.Address
import com.turnover.my.taste.app.domain.embedded.BusinessTime
import jakarta.persistence.*

@Entity
@Table(name = "store", schema = "app")
class Store(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    val id: Long? = null,

    val name: String,

    val lat: Double,

    val lon: Double,

    @Column(name = "phone_number")
    val phoneNumber: String?,

    val homepage: String,

    val notice: String,

    val intro: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "store_status", columnDefinition = "varchar(20) default OPEN")
    val storeStatus: StoreStatus,

    @Enumerated(EnumType.STRING)
    @Column(name = "park_status")
    val parkStatus: ParkStatus,

    @Column(name = "park_detail")
    val parkDetail: String,

    @Embedded
    val address: Address,

    @Embedded
    val businessTime: BusinessTime,

    ) {

}