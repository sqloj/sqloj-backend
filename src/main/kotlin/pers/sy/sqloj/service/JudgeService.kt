package pers.sy.sqloj.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.converter.StringHttpMessageConverter
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import pers.sy.sqloj.api.param.SubmitCountParam
import pers.sy.sqloj.api.param.SubmitSearchParam
import pers.sy.sqloj.entity.JudgeServerDO
import pers.sy.sqloj.entity.QuestionVO
import pers.sy.sqloj.entity.RecordDO
import pers.sy.sqloj.entity.TestcaseVO.Companion.TYPE_REDIS
import pers.sy.sqloj.exception.*
import pers.sy.sqloj.mapper.JudgeServerMapper
import pers.sy.sqloj.mapper.QuestionMapper
import pers.sy.sqloj.mapper.RecordMapper
import pers.sy.sqloj.mapper.TestcaseMapper
import pers.sy.sqloj.util.VResponse
import java.nio.charset.StandardCharsets


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

    fun submit(code: String, questionID: Int, userID: String): RecordDO {
        val question = questionMapper.getByID(questionID) ?: throw QuestionNotFoundException()
        val record = RecordDO(userID, questionID, code)
        recordMapper.insert(record)
        return judge(question, record)
    }

    fun test(code: String, testcaseID: Int): DBType? {
        val testcase = testcaseMapper.getByID(testcaseID) ?: throw TestcaseNotFoundException()
        return if(testcase.judgeTypeID == TYPE_REDIS) {
            val statement = "${testcase.abstract} \n ${testcase.content} \n $code"
            val judgeTypeID = testcase.judgeTypeID
            exec(statement, judgeTypeID)
        } else {
            val statement = "${testcase.abstract} ; ${testcase.content} ; $code"
            val judgeTypeID = testcase.judgeTypeID
            exec(statement, judgeTypeID)
        }
    }

    fun judge(question: QuestionVO, record: RecordDO): RecordDO {
        var codeQuestion:String = ""
        var codeTeacher:String = ""
        var codeStudent:String = ""
        if(question.judgeTypeID == TYPE_REDIS) {
            codeQuestion = "${question.testcaseAbstract} \n ${question.testcaseContent}"
            codeTeacher = "$codeQuestion \n ${question.answer}"
            codeStudent = "$codeQuestion \n ${record.code}"
        } else {
            codeQuestion = "${question.testcaseAbstract} ; ${question.testcaseContent}"
            codeTeacher = "$codeQuestion ; ${question.answer}"
            codeStudent = "$codeQuestion ; ${record.code}"
        }
        try {
            val retStudent = exec(codeStudent, question.judgeTypeID)
            val retTeacher = exec(codeTeacher, question.judgeTypeID)
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

    fun randomServer(judgeTypeID: Int): JudgeServerDO {
        val servers = judgeServerMapper.filterByjudgeTypeID(judgeTypeID)
        if (servers.isEmpty()) {
            throw NoSuchJudgeServerException("没有对应的服务器")
        }
        return servers.random()
    }

    fun exec(statement: String, judgeTypeID: Int): DBType? {
        val server = randomServer(judgeTypeID)
        return exec(statement, server.url, server.password)
    }

    fun exec(statement: String, url: String, password: String): DBType {
        val restTemplate = RestTemplate()
        restTemplate.messageConverters
            .add(0, StringHttpMessageConverter(StandardCharsets.UTF_8))
        println("[LOG] statement = $statement")

        val turl = "$url/api/exec?password=$password"
        val retT = restTemplate.postForObject(turl, statement, VResponse<DBType>().javaClass)
        val ret = retT ?: throw Exception("null ptr")
        println(ret)
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

    fun filter(param: SubmitSearchParam): List<RecordDO> {
        return recordMapper.filter(param)
    }

    fun count(param: SubmitCountParam): Int {
        return recordMapper.count(param)
    }
}