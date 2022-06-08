package pers.sy.sqloj.mapper

import org.apache.ibatis.annotations.Mapper
import pers.sy.sqloj.api.param.ArticleFilterParam
import pers.sy.sqloj.api.param.ArticleParam
import pers.sy.sqloj.entity.ArticleDO
import pers.sy.sqloj.entity.ArticleVO

@Mapper
interface ArticleMapper {

    fun filter(id: ArticleFilterParam): List<ArticleVO>

    fun insert(entity: ArticleDO)

    fun update(entity: ArticleParam)

    fun delete(id: Int)

    fun getVO(id: Int): ArticleVO?

    fun getDO(id: Int): ArticleDO?
}
