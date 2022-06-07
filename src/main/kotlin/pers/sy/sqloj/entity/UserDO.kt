package pers.sy.sqloj.entity

data class UserDO(
    val id: String,
    var username: String,
    var password: String,
    var department: String,
    var signature: String,
    var submit_num: Int,
    var pass_num : Int,
    val role: Int
) {
    companion object {
        const val STUDENT = 1
        const val ADMIN = 2
    }//伴生对象
}
