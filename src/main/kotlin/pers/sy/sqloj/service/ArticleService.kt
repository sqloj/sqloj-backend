package pers.sy.sqloj.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pers.sy.sqloj.api.param.ArticleFilterParam
import pers.sy.sqloj.api.param.ArticleParam
import pers.sy.sqloj.entity.ArticleDO
import pers.sy.sqloj.entity.ArticleVO
import pers.sy.sqloj.exception.ArticleNotFoundException
import pers.sy.sqloj.mapper.ArticleMapper

@Service
class ArticleService
@Autowired constructor(
    val articleMapper: ArticleMapper
) {

    fun insert(param: ArticleParam): ArticleDO {
        val entity = param.toArticleDO()
        articleMapper.insert(entity)
        return entity
    }

    fun update(entity: ArticleParam) {
        articleMapper.update(entity)
    }

    fun delete(id: Int) {
        articleMapper.delete(id)
    }

    fun getVO(id: Int): ArticleVO {
        return articleMapper.getVO(id) ?: throw ArticleNotFoundException()
    }

    fun filter(param: ArticleFilterParam): List<ArticleVO> {
        return articleMapper.filter(param)
    }
}