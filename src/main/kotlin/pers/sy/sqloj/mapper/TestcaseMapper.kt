package pers.sy.sqloj.mapper

import org.apache.ibatis.annotations.Mapper
import pers.sy.sqloj.entity.TestcaseDO

@Mapper
interface TestcaseMapper {

    fun list(): List<TestcaseDO>

    fun delete(id: Int)

    fun getByID(id: Int): TestcaseDO?

    fun update(entity: TestcaseDO)

    fun insert(entity: TestcaseDO)
}