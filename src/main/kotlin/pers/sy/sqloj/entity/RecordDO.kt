package pers.sy.sqloj.entity

import java.time.LocalDateTime

data class RecordDO(
    val id: Int,
    val userID: String,
    val questionID: Int,
    val code: String,
    var result: Int,
    val time: LocalDateTime
) {
    constructor(userID: String, questionID: Int, code: String) : this(
        0,
        userID,
        questionID,
        code,
        RESULT_WAITING,
        LocalDateTime.now()
    )

    companion object {
        const val RESULT_UNKNOWN = 0
        const val RESULT_ACCEPT = 1
        const val RESULT_WAITING = 2
        const val RESULT_WRONG_ANSWER = 3
        const val RESULT_CE = 4
        const val RESULT_SERVER_ERROR = 5
    }
}
