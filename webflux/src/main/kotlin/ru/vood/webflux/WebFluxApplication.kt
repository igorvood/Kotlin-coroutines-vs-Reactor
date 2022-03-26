package ru.vood.webflux

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
//@EnableScheduling
@EnableConfigurationProperties
open class WebFluxApplication

fun main(args: Array<String>) {
    runApplication<WebFluxApplication>(*args)
}
