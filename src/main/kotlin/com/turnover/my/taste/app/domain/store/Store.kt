package com.turnover.my.taste.app.domain.store

import com.turnover.my.taste.app.domain.embedded.Address
import com.turnover.my.taste.app.domain.embedded.BusinessTime
import jakarta.persistence.*

@Entity
@Table(name = "store", schema = "neighbor")
class Store(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    val id: Long,

    val name: String,

    @Column(columnDefinition = "varchar(20) default OPEN")
    @Enumerated(EnumType.STRING)
    val status: StoreStatus,

    @Enumerated(EnumType.STRING)
    val parkAvailable: ParkAvailable,

    @Embedded
    val address: Address,

    @Embedded
    val businessTime: BusinessTime,

) {

}