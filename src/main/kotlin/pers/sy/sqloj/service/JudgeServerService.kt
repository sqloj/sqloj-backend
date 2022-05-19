package pers.sy.sqloj.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import pers.sy.sqloj.entity.JudgeServerDO
import pers.sy.sqloj.entity.PingVO
import pers.sy.sqloj.mapper.JudgeServerMapper
import pers.sy.sqloj.util.VResponse

@Service
class JudgeServerService
@Autowired constructor(
    val judgeServerMapper: JudgeServerMapper
) {

    fun list(): List<JudgeServerDO> {
        return judgeServerMapper.list()
    }

    fun insert(entity: JudgeServerDO) {
        val restTemplate = RestTemplate()
        val turl = "${entity.url}/api/ping?password=${entity.password}"
        val typeDef = object : ParameterizedTypeReference<VResponse<PingVO>>() {}
        val retT = restTemplate.exchange(turl, HttpMethod.POST, null, typeDef)
        val ret = retT.body ?: throw Exception("MULL")
        if (ret.code != VResponse.OK || ret.data == null) {
            throw Exception(ret.toString())
        }
        entity.typeID = ret.data.typeID
        entity.typeName = ret.data.typeName
        judgeServerMapper.insert(entity)
    }

    fun update(entity: JudgeServerDO) {
        judgeServerMapper.update(entity)
    }

    fun delete(id: Int) {
        judgeServerMapper.delete(id)
    }

    fun support(): List<JudgeServerDO> {
        return judgeServerMapper.support()
    }

    fun allSupport(): List<PingVO> {
        return judgeServerMapper.allSupport()
    }
}