package pers.sy.sqloj.entity;

import java.time.LocalDateTime
data class ArticleDO(
    val id : Int,
    var title :String,
    var content : String,
    val time : LocalDateTime,
    val user_id : String
) {
//    constructor() : this(0, "", "", LocalDateTime.now(), "")
//
//    constructor(title : String, content :String,user_id: String) : this(
//        0,
//        title,
//        content,
//        LocalDateTime.now(),
//        user_id
//    )
    companion object {

    }//伴生对象
}

