package pers.sy.sqloj.api.param

import io.swagger.v3.oas.annotations.media.Schema

data class SubmitParam(
    @Schema(description = "题目 ID")
    val questionID: Int,
    @Schema(description = "用户 ID")
    val userID: String,
    @Schema(description = "代码")
    val code: String
)

data class SubmitTestParam(
    @Schema(description = "测试集 ID")
    val testcaseID: Int,
    @Schema(description = "代码")
    val code: String
)
data class SubmitTestcaseParam(
    @Schema(description = "测试集 abstract")
    val abstract: String,
    @Schema(description = "测试集 content")
    val content: String,
    @Schema(description = "数据库类型")
    val judgeTypeID: Int
)

data class SubmitSearchParam(
    @Schema(description = "题目 ID")
    val questionID: Int?,
    @Schema(description = "用户 ID")
    val userID: String?,
    @Schema(description = "结果")
    val result: Int?
)

data class SubmitCountParam(
    @Schema(description = "用户 ID")
    val userID: String,
    @Schema(description = "结果")
    val result: Int
)
