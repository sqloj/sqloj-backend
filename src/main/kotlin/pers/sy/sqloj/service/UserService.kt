package pers.sy.sqloj.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pers.sy.sqloj.entity.UserDO
import pers.sy.sqloj.exception.UserAlreadyExistsException
import pers.sy.sqloj.exception.UserNotFoundException
import pers.sy.sqloj.exception.UserVerifyFailedException
import pers.sy.sqloj.mapper.UserMapper

@Service
class UserService
@Autowired constructor(
    val userMapper: UserMapper
) {

    private fun getUserByID(id: Long): UserDO {
        return userMapper.findByID(id) ?: throw UserNotFoundException()
    }

    fun login(id: Long, password: String): UserDO {
        val user = userMapper.findByID(id)
        if (user == null || user.password != password) {
            throw UserVerifyFailedException()
        }
        return user
    }

    private fun verify(id: Long, password: String): Boolean {
        val user = userMapper.findByID(id)
        if (user == null || user.password != password) {
            return false
        }
        return true
    }

    fun list(): List<UserDO> {
        return userMapper.list()
    }

    fun update(entity: UserDO) {
        userMapper.update(entity)
    }

    fun update(oldPassword: String, entity: UserDO) {
        val user = login(entity.id, oldPassword)
        update(entity)
    }

    fun delete(id: Long) {
        val user = getUserByID(id)
        userMapper.delete(id)
    }

    fun regsiter(user: UserDO) {
        val entity = userMapper.findByID(user.id)
        if (entity != null) {
            throw UserAlreadyExistsException()
        }
        userMapper.insert(user)
    }
}
