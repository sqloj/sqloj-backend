package pers.sy.sqloj.api.param

import io.swagger.v3.oas.annotations.media.Schema


data class QuestionFilterParam(
    @Schema(description = "题目 ID", required = false)
    val id: Int?,
    @Schema(description = "测试集", required = false)
    val testcaseID: Int?
)
