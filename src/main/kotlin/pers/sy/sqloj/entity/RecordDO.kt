package pers.sy.sqloj.entity

import java.time.LocalDateTime

data class RecordDO(
    val id: Int,
    val userID: String,
    val questionID: Int,
    val code: String,
    val result: Int,
    val time: LocalDateTime
) {
    companion object {
        const val RESULT_WA = 0
        const val RESULT_AC = 1
        const val RESULT_CE = 2
    }
}
