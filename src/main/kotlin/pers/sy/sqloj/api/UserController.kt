package pers.sy.sqloj.api

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import pers.sy.sqloj.entity.UserDO
import pers.sy.sqloj.exception.UserVerifyFailedException
import pers.sy.sqloj.service.UserService
import pers.sy.sqloj.util.VResponse

@RestController
@RequestMapping("/api/v1/user")
@Tag(name = "SQLOJ 用户管理", description = "UserController")
class UserController
@Autowired constructor(
    val userService: UserService
) {

    @PostMapping("/login")
    @Operation(summary = "用户登录")
    @ApiResponse(description = "用户信息")
    fun login(
        @RequestParam @Parameter(description = "用户 ID", example = "test") id: String,
        @RequestParam @Parameter(description = "密码", example = "test") password: String
    ): VResponse<Any?> {
        try {
            val user = userService.login(id, password)
            return VResponse.ok(user)
        } catch (e: UserVerifyFailedException) {
            return VResponse.err(1, "用户名或密码错误")
        }
    }

    @PostMapping("/register")
    @Operation(summary = "用户登录")
    @ApiResponse(description = "用户信息")
    fun register(
        @RequestBody @Parameter(description = "用户 ID") entity: UserDO
    ): VResponse<Any?> {
        if (entity.role != UserDO.STUDENT) {
            return VResponse.err(2, "用户组错误")
        }
        try {
            userService.register(entity)
            return VResponse.ok(entity)
        } catch (e: UserVerifyFailedException) {
            return VResponse.err(1, "用户名或密码错误")
        }
    }


    @PostMapping("/update")
    @Operation(summary = "更新用户信息")
    fun updateUsernameByID(
        @RequestParam @Parameter(description = "旧密码", example = "test") oldPassword: String,
        @RequestBody @Parameter(description = "用户信息") entity: UserDO
    ): VResponse<Any?> {
        try {
            userService.update(oldPassword, entity)
            return VResponse.ok()
        } catch (e: Exception) {
            return VResponse.err(1)
        }
    }

    @PostMapping("/delete")
    @Operation(summary = "删除用户")
    fun deleteUsernameByID(
        @RequestParam @Parameter(description = "用户 ID", example = "test") id: String,
    ): VResponse<Any?> {
        try {
            userService.delete(id)
            return VResponse.ok()
        } catch (e: Exception) {
            return VResponse.err(1)
        }
    }
}
