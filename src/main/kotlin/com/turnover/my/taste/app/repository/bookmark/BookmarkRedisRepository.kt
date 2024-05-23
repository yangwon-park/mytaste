package com.turnover.my.taste.app.repository.bookmark

import com.turnover.my.taste.app.domain.bookmark.Bookmark
import org.springframework.data.jpa.repository.JpaRepository

interface BookmarkRedisRepository: JpaRepository<Bookmark, Long> {
}