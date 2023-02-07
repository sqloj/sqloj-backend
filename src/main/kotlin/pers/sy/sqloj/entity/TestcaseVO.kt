package pers.sy.sqloj.entity

data class TestcaseVO(
    val id: Int,
    var label: String,
    var abstract: String,
    var content: String,
    var judgeTypeID: Int,
    val typeName: String
) {
    companion object {
        const val TYPE_MARIADB = 1
        const val TYPE_SQL_SERVER = 2
        const val TYPE_MYSQL = 3
        const val TYPE_H2 = 4
        const val TYPE_REDIS = 5
    }
}
