package pers.sy.sqloj.entity

data class JudgeServerDO(
    val id: Int,
    var url: String,
    var password: String,
    var judgeTypeID: Int,
    var typeName: String
) {
    companion object {
        const val TYPE_MARIADB = 1
        const val TYPE_SQL_SERVER = 2
        const val TYPE_MYSQL = 3
        const val TYPE_H2 = 4
        const val TYPE_REDIS = 5
    }
}
