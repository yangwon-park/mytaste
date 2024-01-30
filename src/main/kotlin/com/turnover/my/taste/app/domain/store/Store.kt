package com.turnover.my.taste.app.domain.store

import com.turnover.my.taste.app.domain.common.BaseEntity
import com.turnover.my.taste.app.domain.common.BaseTimeEntity
import com.turnover.my.taste.app.domain.embedded.Address
import com.turnover.my.taste.app.domain.embedded.BusinessTime
import com.turnover.my.taste.app.domain.menu.Menu
import com.turnover.my.taste.app.domain.store.enums.ParkStatus
import com.turnover.my.taste.app.domain.store.enums.StoreStatus
import jakarta.persistence.*
import org.geolatte.geom.G2D
import org.geolatte.geom.Point


@Entity
@Table(name = "store", schema = "app")
class Store(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    val id: Long? = null,

    val name: String,

    val lat: Double,

    val lon: Double,

    var point: Point<G2D>? = null,

    @Column(name = "phone_number")
    val phoneNumber: String,

    val homepage: String,

    val notice: String,

    val intro: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "varchar(20) default OPEN")
    val status: StoreStatus,

    @Enumerated(EnumType.STRING)
    @Column(name = "park_status")
    val parkStatus: ParkStatus,

    @Column(name = "park_detail")
    val parkDetail: String,

    @Embedded
    val address: Address,

    @Embedded
    val businessTime: BusinessTime,

    ) : BaseEntity() {

    fun addPoint(point: Point<G2D>) {
        this.point = point
    }
}