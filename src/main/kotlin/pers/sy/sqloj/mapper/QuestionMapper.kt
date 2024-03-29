package pers.sy.sqloj.mapper

import org.apache.ibatis.annotations.Mapper
import pers.sy.sqloj.api.param.QuestionFilterParam
import pers.sy.sqloj.entity.QuestionDO
import pers.sy.sqloj.entity.QuestionVO

@Mapper
interface QuestionMapper {

    fun listVO(): List<QuestionVO>

    fun delete(id: Int)

    fun listDO(): List<QuestionDO>

    fun getByID(id: Int): QuestionVO?

    fun update(entity: QuestionDO)

    fun insert(entity: QuestionDO)

    fun filter(param: QuestionFilterParam): List<QuestionDO>
}