package pers.sy.sqloj.entity

data class PingVO(
    val judgeTypeID: Int,
    val typeName: String
) {
    constructor() : this(0, "")
}
