package pers.sy.sqloj.mapper

import org.apache.ibatis.annotations.Mapper
import pers.sy.sqloj.entity.TestcaseVO

@Mapper
interface TestcaseMapper {

    fun list(): List<TestcaseVO>

    fun delete(id: Int)

    fun getByID(id: Int): TestcaseVO?

    fun update(entity: TestcaseVO)

    fun insert(entity: TestcaseVO)
}