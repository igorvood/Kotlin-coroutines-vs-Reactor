package ru.vood.coroutines.controller

import kotlinx.coroutines.delay
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class RequestService2 {
    @Value("\${delay}")
    var delayV: Long = 10

    suspend fun getData(id: String): String {
        delay(delayV)
        return "$id ${RequestService2::class.java}"
    }
}
