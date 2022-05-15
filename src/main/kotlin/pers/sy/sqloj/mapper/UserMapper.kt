package pers.sy.sqloj.mapper

import org.apache.ibatis.annotations.Mapper
import pers.sy.sqloj.entity.UserDO

@Mapper
interface UserMapper {

    fun findByID(id: Long): UserDO?

    fun delete(id: Long)

    fun update(entity: UserDO)

    fun insert(entity: UserDO)

    fun list(): List<UserDO>
}
