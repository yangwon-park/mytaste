package com.turnover.my.taste.app.domain.common

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@EntityListeners(AuditingEntityListener::class)
@MappedSuperclass
open class BaseTimeEntity(
    @CreatedDate
    @Column(name = "created_date", updatable = false)
    val createdDate: LocalDateTime? = null,

    @LastModifiedDate
    @Column(name = "last_modified_date")
    val lastModifiedDate: LocalDateTime? = null
) {

}

