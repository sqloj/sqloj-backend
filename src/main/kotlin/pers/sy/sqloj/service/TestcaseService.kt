package pers.sy.sqloj.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pers.sy.sqloj.entity.TestcaseVO
import pers.sy.sqloj.exception.TestcaseNotFoundException
import pers.sy.sqloj.mapper.TestcaseMapper

@Service
class TestcaseService
@Autowired constructor(
    val testcaseMapper: TestcaseMapper
){
    fun list(): List<TestcaseVO> {
        return testcaseMapper.list()
    }

    fun delete(id: Int) {
        testcaseMapper.delete(id)
    }

    fun getByID(id: Int): TestcaseVO {
        return testcaseMapper.getByID(id) ?: throw TestcaseNotFoundException()
    }

    fun insert(entity: TestcaseVO) {
        testcaseMapper.insert(entity)
    }

    fun update(entity: TestcaseVO) {
        testcaseMapper.update(entity)
    }
}