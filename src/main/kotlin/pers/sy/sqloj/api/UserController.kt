package pers.sy.sqloj.api

import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pers.sy.sqloj.util.VResponse

@RestController
@RequestMapping("/api/v1/user")
@Tag(name = "SQLOJ 用户管理", description = "UserController")
class UserController {

    @PostMapping("/login")
    fun login(username: String, password: String): VResponse<Any?> {
        if (username.equals("admin") && password.equals("admin")) {
            return VResponse.ok();
        } else {
            return VResponse.err(1);
        }
    }
}
