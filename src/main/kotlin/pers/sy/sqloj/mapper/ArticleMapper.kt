package pers.sy.sqloj.mapper

import org.apache.ibatis.annotations.Mapper
import pers.sy.sqloj.entity.ArticleDO

@Mapper
interface ArticleMapper {

    fun list(): List<ArticleDO>

    fun delete(id: Int)

    fun delete2(id : Int)

    fun getByID(id: Int): ArticleDO?

    fun update(entity: ArticleDO)

    fun insert(entity: ArticleDO)

    fun insert2(id :Int, user_id:Int)
}
