package pers.sy.sqloj.mapper

import org.apache.ibatis.annotations.Mapper
import pers.sy.sqloj.entity.JudgeServerDO
import pers.sy.sqloj.entity.PingVO

@Mapper
interface JudgeServerMapper {

    fun list(): List<JudgeServerDO>

    fun allSupport(): List<PingVO>

    fun insert(entity: JudgeServerDO)

    fun insert2(user_id :Int, id : Int)

    fun update(entity: JudgeServerDO)

    fun delete(id: Int)

    fun delete2(id : Int)

    fun filterByTypeID(typeID: Int): List<JudgeServerDO>

    fun support(): List<JudgeServerDO>
}