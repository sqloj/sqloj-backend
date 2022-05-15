package pers.sy.sqloj.entity

data class QuestionDO(
    val id: Int,
    var content: String,
    var answer: String,
    var testcaseID: Int
)

data class QuestionVO(
    val id: Int,
    var content: String,
    var answer: String,
    var testcaseID: Int,
    var label: String,
    var abstract: String,
    var lang: Int
)
