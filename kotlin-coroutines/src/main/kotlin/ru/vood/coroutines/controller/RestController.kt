package ru.vood.coroutines.controller

import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import ru.vood.coroutines.request.DataCollector
import ru.vood.coroutines.request.RequestService1
import ru.vood.coroutines.request.RequestService2

@RestController
class RestController(
    val requestService1: RequestService1,
    val requestService2: RequestService2
) {

    @GetMapping("classic/{id}")
//    @GetMapping("/collectInfo")
    fun collectInfo(@PathVariable id: Int): DataCollector {
        val runBlocking = runBlocking {

//            val id = 2
            val data = requestService1.getData(id.toString())
            val data1 = requestService2.getData(id.toString())
            DataCollector(data.await(), data1.await())
        }
        return runBlocking
    }

}