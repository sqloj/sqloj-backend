package pers.sy.sqloj.entity

data class QuestionDO(
    val id: Int,
    var content: String,
    var answer: String,
    var testcaseID: Int,
    var label: String
)

data class QuestionVO(
    val id: Int,
    var content: String,
    var answer: String,
    var testcaseID: Int,
    var label: String,
    var testcaseAbstract: String,
    var testcaseContent: String,
    var testcaseLabel: String,
    var judgeTypeID: Int,
    var typeName: String
)
