package pers.sy.sqloj.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pers.sy.sqloj.entity.StudentDO
import pers.sy.sqloj.entity.StudentVO
import pers.sy.sqloj.exception.UserNotFoundException
import pers.sy.sqloj.mapper.StudentMapper

@Service
class StudentService
@Autowired constructor(
    val studentMapper: StudentMapper
) {

    fun listVO(): List<StudentVO> {
        return studentMapper.listVO()
    }

    fun getVO(id: String): StudentVO {
        return studentMapper.getVO(id) ?: throw UserNotFoundException()
    }

    fun getDO(id: String): StudentDO {
        return studentMapper.getDO(id) ?: throw UserNotFoundException()
    }

}
