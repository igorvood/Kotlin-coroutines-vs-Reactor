package ru.vood.coroutines

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties
//@EnableSwagger2WebMvc
class CoroutinesApplication

fun main(args: Array<String>) {
    runApplication<CoroutinesApplication>(*args)
}
