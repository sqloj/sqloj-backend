package pers.sy.sqloj.entity

data class JudgeServerDO(
    val id: Int,
    var url: String,
    var password: String,
    var typeID: Int,
    var typeName: String,
    val user_id : Int
) {
    companion object {
        const val TYPE_MARIADB = 1
        const val TYPE_SQL_SERVER = 2
        const val TYPE_MYSQL = 3
        const val TYPE_SQLITE = 4
        const val TYPE_H2 = 5
    }
}
