package ru.vood.webflux.controller

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
open class Config {
    @Bean
    open fun externalFirstService() = WebClient.create("http://localhost:8001/externalFirstService/")

    @Bean
    open fun externalSecondService() = WebClient.create("http://localhost:8002/externalFirstService/")

}