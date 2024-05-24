package com.turnover.my.taste.integration.redis

import com.turnover.my.taste.app.domain.bookmark.Bookmark
import com.turnover.my.taste.app.repository.bookmark.BookmarkRedisRepository
import io.kotest.core.spec.style.StringSpec
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@SpringBootTest
@Transactional(readOnly = true)
@ActiveProfiles("dev")
class RedisTest @Autowired constructor(
    private val bookMarkRedisRepository: BookmarkRedisRepository
) : StringSpec({

    "북마크 저장 테스트" {
        val bookmark = Bookmark(
            12115L,
            "장소1",
            LocalDateTime.now()
        )

        val savedBookMark = bookMarkRedisRepository.save(bookmark)

        println(savedBookMark)

        val all = bookMarkRedisRepository.findAll()

        print(all)
    }
}) {
}