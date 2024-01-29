package com.turnover.my.taste.app.exception

class DuplicateException(
    type: String, value: String
) : RuntimeException("이미 존재하는 $type 입니다. :: $value") {

}