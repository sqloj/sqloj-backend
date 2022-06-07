package pers.sy.sqloj.entity

data class QuestionDO(
    val id: Int = -1,
    var content: String = "",
    var answer: String = "",
    var testcaseID: Int = 0,
    var question_label: String = ""
)

data class QuestionVO(
    val id: Int,
    var content: String,
    var answer: String,
    var testcaseID: Int,
    var question_label: String,
    var testcaseAbstract: String,
    var testcaseContent: String,
    var label: String,
    var typeID: Int,
    var typeName: String
)
