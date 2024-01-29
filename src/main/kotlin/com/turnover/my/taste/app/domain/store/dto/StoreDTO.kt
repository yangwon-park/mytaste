package com.turnover.my.taste.app.domain.store.dto

import com.turnover.my.taste.app.domain.embedded.Address
import com.turnover.my.taste.app.domain.embedded.BusinessTime
import com.turnover.my.taste.app.domain.store.enums.ParkStatus
import com.turnover.my.taste.app.domain.store.Store
import com.turnover.my.taste.app.domain.store.enums.StoreStatus
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import org.geolatte.geom.G2D
import org.geolatte.geom.Point
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalTime

class StoreDTO {

    data class Save(

        @field:NotBlank
        val name: String,

        @field:NotBlank
        val lat: Double,

        @field:NotBlank
        val lon: Double,

        val phoneNumber: String,

        val homepage: String,

        val notice: String,

        val intro: String,

        val parkStatus: ParkStatus,

        val parkDetail: String,

        val zipcode: String,

        val roadAddr: String,

        val numberAddr: String,

        val detailAddr: String,

        @field:NotNull
        @DateTimeFormat(pattern = "HH:mm")
        val openingTime: LocalTime?,

        @field:NotNull
        @DateTimeFormat(pattern = "HH:mm")
        val closingTime: LocalTime?,

        @field:NotNull
        @DateTimeFormat(pattern = "HH:mm")
        val breakStartTime: LocalTime?,

        @field:NotNull
        @DateTimeFormat(pattern = "HH:mm")
        val breakEndTime: LocalTime?,

        @field:NotNull
        @DateTimeFormat(pattern = "HH:mm")
        val lastOrderTime: LocalTime?,

        val offDay: String?,
    ) {

        fun toEntity(point: Point<G2D>): Store {
            return Store(
                name = name,
                lat = lat,
                lon = lon,
                point = point,
                phoneNumber = phoneNumber,
                homepage = homepage,
                notice = notice,
                intro = intro,
                storeStatus = StoreStatus.OPEN,
                parkStatus = parkStatus,
                parkDetail = parkDetail,
                address = Address(roadAddr, numberAddr, zipcode, detailAddr),
                businessTime = BusinessTime(openingTime, closingTime, breakStartTime,
                    breakEndTime, lastOrderTime, offDay),
            )
        }
    }

    data class StorePoints(
        val lat: Double,

        val lon: Double
    ) {

    }

    data class LiteDetails(

        val name: String,

        val address: Address,

        val storeStatus: StoreStatus
    ) {

    }
}