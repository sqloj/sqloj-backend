package pers.sy.sqloj.api

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import pers.sy.sqloj.entity.QuestionDO
import pers.sy.sqloj.exception.QuestionNotFoundException
import pers.sy.sqloj.service.QuestionService
import pers.sy.sqloj.util.VResponse

@RestController
@RequestMapping("/api/v1/question")
@Tag(name = "SQLOJ 题目管理", description = "QuestionController")
class QuestionController @Autowired constructor(
    val questionService: QuestionService
) {
    @GetMapping("/list")
    @Operation(summary = "题目列表")
    @ApiResponse(description = "题目列表")
    fun list(): VResponse<Any?> {
        return VResponse.ok(questionService.listDO())
    }

    @GetMapping("/listWithTestcase")
    @Operation(summary = "题目列表")
    @ApiResponse(description = "题目列表")
    fun listWithTestcase(): VResponse<Any?> {
        return VResponse.ok(questionService.listVO())
    }

    @GetMapping("/info/{id}")
    @Operation(summary = "题目信息")
    @ApiResponse(description = "题目信息")
    fun info(
        @PathVariable("id") @Parameter(description = "题目 ID") id: Int
    ): VResponse<Any?> {
        try {
            val question = questionService.getByID(id)
            return VResponse.ok(question)
        } catch (e: QuestionNotFoundException) {
            return VResponse.err(1, "题目不存在")
        }
    }
    @GetMapping("/info2/{testcase-id}")
    @Operation(summary = "题目信息")
    @ApiResponse(description = "题目信息")
    fun info2(
        @PathVariable("testcase-id") @Parameter(description = "测试用例 ID") id: Int
    ): VResponse<Any?> {
        try {
            val question = questionService.getByTestcaseID(id)
            return VResponse.ok(question)
        } catch (e: QuestionNotFoundException) {
            return VResponse.err(1, "题目不存在")
        }
    }
    @PostMapping("/insert")
    @Operation(summary = "增加题目")
    fun insert(
        @RequestBody @Parameter(description = "题目信息") entity: QuestionDO
    ): VResponse<Any?> {
        try {
            questionService.insert(entity)
            return VResponse.ok(entity)
        } catch (e: QuestionNotFoundException) {
            return VResponse.err(1, "题目不存在")
        } catch (e: Exception) {
            return VResponse.err(2, "加入失败")
        }
    }

    @PostMapping("/update")
    @Operation(summary = "更新题目")
    fun update(
        @RequestBody @Parameter(description = "题目信息") entity: QuestionDO
    ): VResponse<Any?> {
        try {
            questionService.update(entity)
            return VResponse.ok(entity)
        } catch (e: QuestionNotFoundException) {
            return VResponse.err(1, "题目不存在")
        } catch (e: Exception) {
            return VResponse.err(2, "更新题目失败")
        }
    }

    @PostMapping("/delete")
    @Operation(summary = "删除题目")
    fun update(
        @RequestParam @Parameter(description = "题目信息") id: Int
    ): VResponse<Any?> {
        try {
            questionService.delete(id)
            return VResponse.ok()
        } catch (e: QuestionNotFoundException) {
            return VResponse.err(1, "题目不存在")
        } catch (e: Exception) {
            return VResponse.err(2, "更新题目失败")
        }
    }
}