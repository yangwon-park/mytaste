package com.turnover.my.taste.app.domain.common

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.jpa.domain.support.AuditingEntityListener

@EntityListeners(AuditingEntityListener::class)
@MappedSuperclass
open class BaseEntity(
    @CreatedBy
    @Column(name = "created_by", updatable = false)
    var createdBy: String? = null,

    @LastModifiedBy
    @Column(name = "last_modified_by")
    var lastModifiedBy: String? = null
) : BaseTimeEntity() {


}