package pers.sy.sqloj.api

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import pers.sy.sqloj.entity.TestcaseVO
import pers.sy.sqloj.exception.TestcaseNotFoundException
import pers.sy.sqloj.service.TestcaseService
import pers.sy.sqloj.util.VResponse

@RestController
@RequestMapping("/api/v1/testcase")
@Tag(name = "SQLOJ 测试集管理", description = "TestcaseController")
class TestcaseController @Autowired constructor(
    val testcaseService: TestcaseService
) {

    @GetMapping("/list")
    @Operation(summary = "测试集列表")
    @ApiResponse(description = "测试集列表")
    fun list(): VResponse<Any?> {
        return VResponse.ok(testcaseService.list())
    }

    @GetMapping("/info/{id}")
    @Operation(summary = "测试集信息")
    @ApiResponse(description = "测试集信息")
    fun info(
        @PathVariable("id") @Parameter(description = "测试集 ID") id: Int): VResponse<Any?> {
        try {
            val testcase = testcaseService.getByID(id)
            return VResponse.ok(testcase)
        } catch (e: TestcaseNotFoundException) {
            return VResponse.err(1, "测试集不存在")
        }
    }

    @PostMapping("/insert")
    @Operation(summary = "增加测试集")
    fun insert(
        @RequestBody @Parameter(description = "测试集信息") entity: TestcaseVO
    ): VResponse<Any?> {
        try {
            testcaseService.insert(entity)
            return VResponse.ok(entity)
        } catch (e: TestcaseNotFoundException) {
            return VResponse.err(1, "测试集不存在")
        } catch (e: Exception) {
            return VResponse.err(2, "加入失败")
        }
    }

    @PostMapping("/update")
    @Operation(summary = "更新测试集")
    fun update(
        @RequestBody @Parameter(description = "测试集信息") entity: TestcaseVO
    ): VResponse<Any?> {
        try {
            testcaseService.update(entity)
            return VResponse.ok(entity)
        } catch (e: TestcaseNotFoundException) {
            return VResponse.err(1, "测试集不存在")
        } catch (e: Exception) {
            return VResponse.err(2, "更新测试集失败")
        }
    }

    @PostMapping("/delete")
    @Operation(summary = "删除测试集")
    fun update(
        @RequestParam @Parameter(description = "测试集信息") id: Int
    ): VResponse<Any?> {
        try {
            testcaseService.delete(id)
            return VResponse.ok()
        } catch (e: TestcaseNotFoundException) {
            return VResponse.err(1, "测试集不存在")
        } catch (e: Exception) {
            return VResponse.err(2, "更新测试集失败")
        }
    }
}