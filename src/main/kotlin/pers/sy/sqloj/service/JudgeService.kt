package pers.sy.sqloj.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import pers.sy.sqloj.entity.JudgeServerDO
import pers.sy.sqloj.entity.QuestionVO
import pers.sy.sqloj.entity.RecordDO
import pers.sy.sqloj.exception.*
import pers.sy.sqloj.mapper.JudgeServerMapper
import pers.sy.sqloj.mapper.QuestionMapper
import pers.sy.sqloj.mapper.RecordMapper
import pers.sy.sqloj.mapper.TestcaseMapper
import pers.sy.sqloj.util.VResponse


typealias DBType = MutableList<Map<*, *>>

@Service
class JudgeService
@Autowired constructor(
    val questionMapper: QuestionMapper,
    val recordMapper: RecordMapper,
    val judgeServerMapper: JudgeServerMapper,
    val testcaseMapper: TestcaseMapper
) {

    fun rejudge(recordID: Int): RecordDO {
        val record = recordMapper.getByID(recordID) ?: throw RecordNotFoundException()
        val question = questionMapper.getByID(record.questionID) ?: throw QuestionNotFoundException()

        record.result = RecordDO.RESULT_UNKNOWN
        recordMapper.update(record)
        return judge(question, record)
    }

    fun sumbit(code: String, questionID: Int, userID: String): RecordDO {
        val question = questionMapper.getByID(questionID) ?: throw QuestionNotFoundException()
        val record = RecordDO(userID, questionID, code)
        recordMapper.insert(record)
        return judge(question, record)
    }

    fun test(code: String, testcaseID: Int): DBType? {
        val testcase = testcaseMapper.getByID(testcaseID) ?: throw TestcaseNotFoundException()
        val statement = "${testcase.abstract} ; ${testcase.content} ; $code"
        val typeID = testcase.typeID
        return exec(statement, typeID)
    }

    fun judge(question: QuestionVO, record: RecordDO): RecordDO {
        val codeQuestion = "${question.testcaseAbstract} ; ${question.testcaseContent}"
        val codeTeacher = "$codeQuestion ; ${question.answer}"
        val codeStudent = "$codeQuestion ; ${record.code}"
        try {
            val retStudent = exec(codeStudent, question.typeID)
            val retTeacher = exec(codeTeacher, question.typeID)
            if (retStudent == retTeacher) {
                record.result = RecordDO.RESULT_ACCEPT
            } else {
                record.result = RecordDO.RESULT_WRONG_ANSWER
            }
        } catch (e: JudgeServerPasswordException) {
            record.result = RecordDO.RESULT_SERVER_ERROR
        } catch (e: Exception) {
            e.printStackTrace()
            record.result = RecordDO.RESULT_CE
        }
        recordMapper.update(record)
        return record
    }

    fun randomServer(typeID: Int): JudgeServerDO {
        val servers = judgeServerMapper.filterByTypeID(typeID)
        if (servers.isEmpty()) {
            throw NoSuchJudgeServerException("没有对应的服务器")
        }
        return servers.random()
    }

    fun exec(statement: String, typeID: Int): DBType? {
        val server = randomServer(typeID)
        return exec(statement, server.url, server.password)
    }

    fun exec(statement: String, url: String, password: String): DBType {
        val restTemplate = RestTemplate()
        println("[LOG] statement = $statement")

        val turl = "$url/api/exec?password=$password"
        val retT = restTemplate.postForObject(turl, statement, VResponse<DBType>().javaClass)
        val ret = retT ?: throw Exception("null ptr")
        if (ret.code == 1) {
            throw JudgeServerPasswordException()
        } else if (ret.code == 2) {
            throw Exception(ret.message)
        }
        return ret.data!!
    }

    fun list(): List<RecordDO> {
        return recordMapper.list()
    }
}