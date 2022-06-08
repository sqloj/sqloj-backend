package pers.sy.sqloj.entity

data class StudentDO (
    val userID: String,
    val submitNum: Int,
    val passNum: Int
)

data class StudentVO(
    val id: String,
    var username: String,
    var department: String,
    var signature: String,
    val submitNum: Int,
    val passNum: Int,
    val role: Int
)
