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
    var testcaseAbstract: String,
    var testcaseContent: String,
    var label: String,
    var lang: Int
)
