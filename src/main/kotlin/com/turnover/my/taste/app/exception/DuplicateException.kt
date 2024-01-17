package com.turnover.my.taste.app.exception

class DuplicateException : RuntimeException {

    constructor(type: String, value: String) : super("이미 존재하는 $type 입니다. :: $value")
}