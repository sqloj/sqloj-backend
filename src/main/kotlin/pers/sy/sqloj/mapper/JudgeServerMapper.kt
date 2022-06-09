package pers.sy.sqloj.mapper

import org.apache.ibatis.annotations.Mapper
import pers.sy.sqloj.entity.JudgeServerDO
import pers.sy.sqloj.entity.PingVO

@Mapper
interface JudgeServerMapper {

    fun list(): List<JudgeServerDO>

    fun allSupport(): List<PingVO>

    fun insert(entity: JudgeServerDO)

    fun update(entity: JudgeServerDO)

    fun delete(id: Int)

    fun filterByjudgeTypeID(judgeTypeID: Int): List<JudgeServerDO>

    fun support(): List<JudgeServerDO>
}