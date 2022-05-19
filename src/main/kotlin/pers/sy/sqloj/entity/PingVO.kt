package pers.sy.sqloj.entity

data class PingVO(
    val typeID: Int,
    val typeName: String
) {
    constructor() : this(0, "") {}
}
