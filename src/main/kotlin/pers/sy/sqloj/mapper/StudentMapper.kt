package pers.sy.sqloj.mapper

import org.apache.ibatis.annotations.Mapper
import pers.sy.sqloj.entity.StudentDO
import pers.sy.sqloj.entity.StudentVO


@Mapper
interface StudentMapper {

    fun listVO(): List<StudentVO>

    fun getVO(id: String): StudentVO?

    fun getDO(id: String): StudentDO?

    fun insert(id: String)

    fun delete(id: String)
}