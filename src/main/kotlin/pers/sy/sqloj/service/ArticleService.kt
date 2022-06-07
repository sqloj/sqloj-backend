package pers.sy.sqloj.service;

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pers.sy.sqloj.entity.ArticleDO
import pers.sy.sqloj.exception.ArticleNotFoundException
import pers.sy.sqloj.mapper.ArticleMapper

@Service
class ArticleService
@Autowired constructor(
    val articleMapper: ArticleMapper
) {

    fun list(): List<ArticleDO> {
        return articleMapper.list()
    }

    fun getByID(id: String): List<ArticleDO> {
        return articleMapper.getByID(id) ?: throw ArticleNotFoundException()
    }

    fun insert(entity: ArticleDO) {
        articleMapper.insert(entity)
        val userid = entity.user_id
        val id = entity.id
        articleMapper.insert2(id, userid)
    }

    fun update(entity: ArticleDO) {
        articleMapper.update(entity)
    }

    fun delete(id: Int) {
        articleMapper.delete(id)
        articleMapper.delete2(id);
    }
}
