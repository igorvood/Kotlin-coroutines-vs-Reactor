package ru.vood.coroutines.controller

import kotlinx.coroutines.delay
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class RequestService1 {

    @Value("\${delay}")
    var delayV: Long = 10

    suspend fun getData(id: String): String {
        delay(delayV)
        return "$id ${RequestService1::class.java}"
    }
}
