package ru.vood.coroutines.experemental

import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.reactive.function.server.*
import org.springframework.web.reactive.function.server.bodyAndAwait
import ru.vood.coroutines.request.DataCollector
import ru.vood.coroutines.request.RequestService1
import ru.vood.coroutines.request.RequestService2

@Component
class RestControllerCoroutine(
    val requestService1: RequestService1,
    val requestService2: RequestService2
) {

//    @GetMapping("classic/{id}")
//    @GetMapping("/collectInfo")
    suspend fun collectInfo(request: ServerRequest): ServerResponse {

//        val id = request.pathVariable("id")

        val dataCollector =            DataCollector(requestService1.getData("id"), requestService2.getData("id"))
        val json = ServerResponse.ok().json()
        val bodyAndAwait = json.bodyValueAndAwait(dataCollector)


        return bodyAndAwait
//        return runBlocking
    }

}
