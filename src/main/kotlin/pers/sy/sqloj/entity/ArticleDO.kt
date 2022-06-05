package pers.sy.sqloj.entity;

import java.time.LocalDateTime
data class ArticleDO(
    val id : Int,
    var title :String,
    var content : String,
    val time : LocalDateTime,
    val user_id : Int
) {
    //    constructor() : this(0, "", "", LocalDateTime.now(), 0)
//
//    constructor(title : String, content :String,user_id: Int) : this(
//        0,
//        title,
//        content,
//        LocalDateTime.now(),
//        user_id
//    )
    companion object {

    }//伴生对象
}

