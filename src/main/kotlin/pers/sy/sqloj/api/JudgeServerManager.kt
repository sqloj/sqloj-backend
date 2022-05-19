package pers.sy.sqloj.api

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import pers.sy.sqloj.entity.JudgeServerDO
import pers.sy.sqloj.exception.JudgeServerNotFoundException
import pers.sy.sqloj.service.JudgeServerService
import pers.sy.sqloj.util.VResponse

@RestController
@RequestMapping("/api/v1/judge")
@Tag(name = "SQLOJ 评测机管理", description = "JudgeServerManager")
class JudgeServerManager
@Autowired constructor(
    val judgeServerService: JudgeServerService
) {

    @GetMapping("/list")
    @Operation(summary = "评测机列表")
    @ApiResponse(description = "评测机列表")
    fun list(): VResponse<Any?> {
        return VResponse.ok(judgeServerService.list())
    }

    @PostMapping("/insert")
    @Operation(summary = "增加评测机")
    fun insert(
        @RequestBody @Parameter(description = "评测机信息") entity: JudgeServerDO
    ): VResponse<Any?> {
        try {
            judgeServerService.insert(entity)
            return VResponse.ok(entity)
        } catch (e: JudgeServerNotFoundException) {
            return VResponse.err(1, "评测机不存在")
        } catch (e: Exception) {
            e.printStackTrace()
            return VResponse.err(2, "加入失败:${e.message}")
        }
    }

    @PostMapping("/update")
    @Operation(summary = "更新评测机")
    fun update(
        @RequestBody @Parameter(description = "评测机信息") entity: JudgeServerDO
    ): VResponse<Any?> {
        try {
            judgeServerService.update(entity)
            return VResponse.ok(entity)
        } catch (e: JudgeServerNotFoundException) {
            return VResponse.err(1, "评测机不存在")
        } catch (e: Exception) {
            return VResponse.err(2, "更新评测机失败")
        }
    }

    @PostMapping("/delete")
    @Operation(summary = "删除评测机")
    fun update(
        @RequestParam @Parameter(description = "评测机信息") id: Int
    ): VResponse<Any?> {
        try {
            judgeServerService.delete(id)
            return VResponse.ok()
        } catch (e: JudgeServerNotFoundException) {
            return VResponse.err(1, "评测机不存在")
        } catch (e: Exception) {
            return VResponse.err(2, "删除评测机失败")
        }
    }

    @GetMapping("/support")
    @Operation(summary = "支持语言")
    fun support(
    ): VResponse<Any?> {
        return VResponse.ok(judgeServerService.support())
    }

    @GetMapping("/allSupport")
    @Operation(summary = "支持语言")
    fun allSupport(
    ): VResponse<Any?> {
        return VResponse.ok(judgeServerService.allSupport())
    }
}