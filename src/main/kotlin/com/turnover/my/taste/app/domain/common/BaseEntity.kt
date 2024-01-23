package com.turnover.my.taste.app.domain.common

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.jpa.domain.support.AuditingEntityListener

@EntityListeners(AuditingEntityListener::class)
@MappedSuperclass
class BaseEntity(
    @CreatedBy
    @Column(name = "created_by", updatable = false)
    val createdBy: String? = null,

    @LastModifiedBy
    @Column(name = "last_modified_by")
    val lastModifiedBy: String? = null
) : BaseTimeEntity() {


}