package pers.sy.sqloj.entity

data class UserDO(
    val id: Long,
    var username: String,
    var password: String,
    var department: String,
    val role: Int
) {
    companion object {
        const val STUDENT = 1
        const val ADMIN = 2
    }
}
