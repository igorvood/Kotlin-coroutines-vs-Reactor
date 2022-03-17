package ru.vood.coroutines

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties
//@EnableSwagger2WebMvc
@EnableAsync
class ClassicRestApplication

fun main(args: Array<String>) {
    runApplication<ClassicRestApplication>(*args)
}
