package pers.sy.sqloj.entity

data class UserDO(
    val id: String,
    var username: String,
    var password: String,
    var department: String,
    var signature: String,
    val role: Int
) {
    companion object {
        const val STUDENT = 1
        const val ADMIN = 2
    }
}

data class UserVO(
    val id: String = "",
    var username: String = "",
    var department: String = "",
    var signature: String = "",
    val role: Int = 0
)
