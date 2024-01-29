package com.turnover.my.taste.app.exception

class EntityNotFoundException(
    entityType: String, entityId: Long
) : RuntimeException("존재하지 않는 $entityType 입니다. ID :: $entityId") {

}