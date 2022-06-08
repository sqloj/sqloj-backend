package pers.sy.sqloj.api.param

import io.swagger.v3.oas.annotations.media.Schema
import pers.sy.sqloj.entity.ArticleDO

data class ArticleParam(
    val id: Int,
    val userID: String,
    val title: String,
    val content: String
) {
    fun toArticleDO(): ArticleDO {
        return ArticleDO(id, userID, title, content)
    }
}

data class ArticleFilterParam(
    @Schema(description = "文章 ID", required = false)
    val id: Int?,
    @Schema(description = "用户 ID", required = false)
    val userID: String?,
    @Schema(description = "标题", required = false)
    val title: String?,
    @Schema(description = "内容", required = false)
    val content: String?
)
