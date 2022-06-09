package pers.sy.sqloj.mapper

import org.apache.ibatis.annotations.Mapper
import pers.sy.sqloj.api.param.SubmitCountParam
import pers.sy.sqloj.api.param.SubmitSearchParam
import pers.sy.sqloj.entity.RecordDO

@Mapper
interface RecordMapper {

    fun list(): List<RecordDO>

    fun insert(entity: RecordDO)

    fun update(entity: RecordDO)

    fun getByID(id: Int): RecordDO?

    fun filter(param: SubmitSearchParam): List<RecordDO>

    fun count(param: SubmitCountParam): Int
}