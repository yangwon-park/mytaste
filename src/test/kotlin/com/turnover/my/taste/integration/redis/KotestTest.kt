package com.turnover.my.taste.integration.redis

import io.kotest.core.spec.style.StringSpec
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
@ActiveProfiles("dev")
class KotestTest: StringSpec({

    "동작 확인" {

    }
}) {
}