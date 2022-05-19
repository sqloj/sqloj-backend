package pers.sy.sqloj.entity

data class TestcaseVO(
    val id: Int,
    var label: String,
    var abstract: String,
    var content: String,
    var typeID: Int,
    val typeName: String
) {
    companion object {
        const val TYPE_MARIADB = 1
        const val TYPE_SQLSERVER = 2
        const val TYPE_MYSQL = 2
    }
}
