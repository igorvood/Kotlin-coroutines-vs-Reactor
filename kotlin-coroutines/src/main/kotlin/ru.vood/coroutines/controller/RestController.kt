package ru.vood.coroutines.controller

import kotlinx.coroutines.runBlocking
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

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
            DataCollector(requestService1.getData(id.toString()), requestService2.getData(id.toString()))
        }
        return runBlocking
    }

}