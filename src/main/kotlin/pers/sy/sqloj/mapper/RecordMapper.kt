package pers.sy.sqloj.mapper

import org.apache.ibatis.annotations.Mapper
import pers.sy.sqloj.entity.RecordDO

@Mapper
interface RecordMapper {

    fun list(): List<RecordDO>

    fun insert(entity: RecordDO)

    fun update(entity: RecordDO)

    fun getByID(id: Int): RecordDO?
}