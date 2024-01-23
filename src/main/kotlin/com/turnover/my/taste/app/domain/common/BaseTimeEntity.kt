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
    var createdDate: LocalDateTime? = LocalDateTime.now(),

    @LastModifiedDate
    @Column(name = "last_modified_date")
    var lastModifiedDate: LocalDateTime? = LocalDateTime.now()
) {

}

