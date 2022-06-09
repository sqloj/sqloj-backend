package pers.sy.sqloj.api

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pers.sy.sqloj.exception.UserNotFoundException
import pers.sy.sqloj.service.StudentService
import pers.sy.sqloj.util.VResponse

@RestController
@RequestMapping("/api/v1/student")
@Tag(name = "SQLOJ 学生管理", description = "StudentController")
class StudentController
@Autowired constructor(
    val studentService: StudentService
) {
    @GetMapping("/list")
    @Operation(summary = "学生列表")
    @ApiResponse(description = "学生列表")
    fun list(): VResponse<Any?> {
        return VResponse.ok(studentService.listVO())
    }

    @GetMapping("/info/{id}")
    @Operation(summary = "学生列表")
    @ApiResponse(description = "学生列表")
    fun info(
        @PathVariable("id") @Parameter(description = "学生 ID") id: String
    ): VResponse<Any?> {
        try {
            val ret = studentService.getVO(id)
            return VResponse.ok(ret)
        } catch (e: UserNotFoundException) {
            return VResponse.err(1, "用户不存在")
        }
    }

}