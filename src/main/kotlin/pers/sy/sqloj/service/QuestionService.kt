package pers.sy.sqloj.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pers.sy.sqloj.entity.QuestionDO
import pers.sy.sqloj.entity.QuestionVO
import pers.sy.sqloj.exception.QuestionNotFoundException
import pers.sy.sqloj.mapper.QuestionMapper

@Service
class QuestionService
@Autowired constructor(
    val questionMapper: QuestionMapper
) {

    fun listVO(): List<QuestionVO> {
        return questionMapper.listVO()
    }

    fun listDO(): List<QuestionDO> {
        return questionMapper.listDO()
    }

    fun getByID(id: Int): QuestionVO {
        return questionMapper.getByID(id) ?: throw QuestionNotFoundException()
    }

    fun insert(entity: QuestionDO) {
        questionMapper.insert(entity)
    }

    fun update(entity: QuestionDO) {
        questionMapper.update(entity)
    }

    fun delete(id: Int) {
        val question = getByID(id)
        questionMapper.delete(id)
    }
}