package ru.vood.coroutines.experemental

import io.micrometer.core.instrument.MeterRegistry
import org.springframework.http.MediaType
import org.springframework.scheduling.annotation.Async
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import ru.vood.coroutines.request.DataCollector
import ru.vood.coroutines.request.RequestService1
import ru.vood.coroutines.request.RequestService2

@RestController
open class UserController(

    val requestService1: RequestService1,
    val requestService2: RequestService2,
    private val meterRegistry: MeterRegistry,

    ) : ILogging by LoggingImp<UserController>("service-user") {

    private val counter = meterRegistry.counter("letter.rps")

//    @Async
    @GetMapping(value = ["coroutine/{id}"]/*, consumes = [MediaType.APPLICATION_NDJSON_VALUE]*/)
    open suspend fun info(
    @PathVariable("id") id: String,
//        response: ServerHttpResponse
    ): Response = logRequest {

        val data = requestService1.getDataAsync(id)
        val data1 = requestService2.getDataAsync(id)
        counter.increment()
        DataCollector(
            data.await(),
            data1.await()
        )
        /*val user = users[userId] ?: run {
            response.statusCode = HttpStatus.NOT_FOUND
            return@logRequest NotFoundResponse(
                error = "user with id='$userId' not found"
            )
        }*/

    }
}