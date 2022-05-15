package pers.sy.sqloj.entity

data class TestcaseDO(
    val id: Int,
    var label: String,
    var abstract: String,
    var content: String,
    var lang: Int
) {
    companion object {
        const val LANG_MYSQL = 1
        const val LANG_SQLSERVER = 2
    }
}
