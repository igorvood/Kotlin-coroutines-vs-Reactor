package ru.vood.webflux.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import ru.vood.webflux.request.DataCollector
import ru.vood.webflux.request.RequestService1
import ru.vood.webflux.request.RequestService2


@RestController
class RestController(
    val requestService1: RequestService1,
    val requestService2: RequestService2,

    ) {

    @GetMapping("webfluf/{id}")
    fun collectInfo(@PathVariable id: String): Mono<DataCollector> {
        val dataAsync = requestService1.getDataAsync(id)
            .flatMap { q1 ->
                requestService2.getDataAsync(id)
                    .map { q2 -> q1 to q2 }
            }
            .map { DataCollector(it.first, it.second) }
        return dataAsync
    }


}