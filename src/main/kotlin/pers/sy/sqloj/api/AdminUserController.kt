package pers.sy.sqloj.api

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pers.sy.sqloj.service.UserService
import pers.sy.sqloj.util.VResponse

@RestController
@RequestMapping("/mapi/v1/user")
@Tag(name = "SQLOJ 用户管理(admin)", description = "AdminUserController")
class AdminUserController @Autowired constructor(
    val userService: UserService
) {
    @GetMapping("/list")
    @Operation(summary = "用户列表")
    @ApiResponse(description = "用户信息")
    fun list(): VResponse<Any?> {
        return VResponse.ok(userService.list())
    }
}