package pers.sy.sqloj.entity

import java.time.LocalDateTime

data class ArticleDO(
    val id: Int = -1,
    val userID: String = "",
    var title: String = "",
    var content: String = "",
    var updateTime: LocalDateTime = LocalDateTime.now()
)

data class ArticleVO(
    val id: Int,
    val userID: String,
    val username: String,
    val signature: String,
    var title: String,
    var content: String,
    val updateTime: LocalDateTime
)
