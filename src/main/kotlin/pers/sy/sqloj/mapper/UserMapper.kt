package pers.sy.sqloj.mapper

import org.apache.ibatis.annotations.Mapper
import pers.sy.sqloj.entity.UserDO
import pers.sy.sqloj.entity.UserVO

@Mapper
interface UserMapper {

    fun findByID(id: String): UserDO?

    fun delete(id: String)

    fun update(entity: UserDO)

    fun insert(entity: UserDO)

    fun list(): List<UserVO>

    fun filter(id: String?, username: String?, department: String?, role: Int?): List<UserVO>
}
