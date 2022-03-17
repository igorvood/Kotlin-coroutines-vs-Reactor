package ru.vood.coroutines.experemental

import org.springframework.scheduling.annotation.Async
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import ru.vood.coroutines.request.DataCollector
import ru.vood.coroutines.request.RequestService1
import ru.vood.coroutines.request.RequestService2

@RestController
class UserController(

    val requestService1: RequestService1,
    val requestService2: RequestService2

) : ILogging by LoggingImp<UserController>("service-user") {

    @Async
    @GetMapping(value = ["coroutine/{id}"])
    suspend fun info(
        @PathVariable("id") userId: Int,
//        response: ServerHttpResponse
    ): Response = logRequest {

        val data = requestService1.getData(userId.toString())
        val data1 = requestService2.getData(userId.toString())
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