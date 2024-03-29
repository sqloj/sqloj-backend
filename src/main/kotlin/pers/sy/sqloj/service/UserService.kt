package pers.sy.sqloj.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pers.sy.sqloj.entity.UserDO
import pers.sy.sqloj.entity.UserVO
import pers.sy.sqloj.exception.UserAlreadyExistsException
import pers.sy.sqloj.exception.UserNotFoundException
import pers.sy.sqloj.exception.UserVerifyFailedException
import pers.sy.sqloj.mapper.StudentMapper
import pers.sy.sqloj.mapper.UserMapper

@Service
class UserService
@Autowired constructor(
    val userMapper: UserMapper,
    val studentMapper: StudentMapper
) {

    private fun getUserByID(id: String): UserDO {
        return userMapper.findByID(id) ?: throw UserNotFoundException()
    }

    fun login(id: String, password: String): UserDO {
        val user = userMapper.findByID(id)
        if (user == null || user.password != password) {
            throw UserVerifyFailedException()
        }
        return user
    }

    private fun verify(id: String, password: String): Boolean {
        val user = userMapper.findByID(id)
        if (user == null || user.password != password) {
            return false
        }
        return true
    }

    fun list(): List<UserVO> {
        return userMapper.list()
    }

    fun update(entity: UserDO) {
        userMapper.update(entity)
    }

    fun update(oldPassword: String, entity: UserDO) {
        val user = login(entity.id, oldPassword)
        update(entity)
    }

    fun delete(id: String) {
        val user = getUserByID(id)
        if (user.role == UserDO.STUDENT) {
            studentMapper.delete(user.id)
        }
        userMapper.delete(id)
    }

    fun register(user: UserDO) {
        val entity = userMapper.findByID(user.id)
        if (entity != null) {
            throw UserAlreadyExistsException()
        }
        userMapper.insert(user)
        if (user.role == UserDO.STUDENT) {
            studentMapper.insert(user.id)
        }
    }

    fun filter(id: String?, username: String?, department: String?, role: Int?): List<UserVO> {
        return userMapper.filter(id, username, department, role)
    }
}
