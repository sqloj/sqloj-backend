package pers.sy.sqloj

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.util.*
import javax.annotation.PostConstruct

@SpringBootApplication
class SqlojBackendApplication

@PostConstruct
fun started() {
    TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"))
}

fun main(args: Array<String>) {
    runApplication<SqlojBackendApplication>(*args)
}
