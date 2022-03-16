package ru.vood.coroutines.experemental

import kotlinx.coroutines.FlowPreview
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class RouterConfiguration {

    @FlowPreview
    @Bean
    fun productRoutes(productsHandler: RestControllerCoroutine) = coRouter {
//        GET("/", productsHandler::findAll)
        GET("/coroutine", productsHandler::collectInfo)
//        GET("/{id}/stock", productsHandler::findOneInStock)
    }

}