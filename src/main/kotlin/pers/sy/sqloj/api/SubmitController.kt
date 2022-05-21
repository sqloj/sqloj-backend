package pers.sy.sqloj.api

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import pers.sy.sqloj.api.param.SubmitParam
import pers.sy.sqloj.api.param.SubmitTestParam
import pers.sy.sqloj.service.JudgeService
import pers.sy.sqloj.util.VResponse

@RestController
@RequestMapping("/api/v1/submit")
@Tag(name = "SQLOJ 提交管理", description = "SubmitController")
class SubmitController
@Autowired constructor(
    val judgeService: JudgeService
) {

    @PostMapping("/test")
    @Operation(summary = "测试运行")
    fun exec(
        @RequestBody param: SubmitTestParam
    ): VResponse<Any?> {
        try {
            val ret = judgeService.test(param.code, param.testcaseID)
            return VResponse.ok(ret)
        } catch (e: Exception) {
            e.printStackTrace()
            return VResponse.err(1, e.message)
        }
    }

    @PostMapping("/submit")
    fun submit(
        @RequestBody param: SubmitParam
    ): VResponse<Any?> {
        try {
            val ret = judgeService.sumbit(param.code, param.questionID, param.userID)
            return VResponse.ok(ret)
        } catch (e: Exception) {
            return VResponse.err(1, "提交失败")
        }
    }

    @PostMapping("/rejudge")
    fun rejudge(
        @RequestParam recordID: Int
    ): VResponse<Any?> {
        try {
            val ret = judgeService.rejudge(recordID)
            return VResponse.ok(ret)
        } catch (e: Exception) {
            return VResponse.err(1, "重测失败")
        }
    }

    @GetMapping("/list")
    fun list(): VResponse<Any?> {
        return VResponse.ok(judgeService.list())
    }
}