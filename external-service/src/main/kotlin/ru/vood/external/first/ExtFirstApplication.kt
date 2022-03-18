package ru.vood.external.first

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync

@SpringBootApplication
@EnableConfigurationProperties
@EnableAsync
open class ExtFirstApplication

fun main(args: Array<String>) {
    runApplication<ExtFirstApplication>(*args)
}
