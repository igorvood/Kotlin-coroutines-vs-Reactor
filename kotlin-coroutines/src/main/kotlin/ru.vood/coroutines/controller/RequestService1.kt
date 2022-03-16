package ru.vood.coroutines.controller

import kotlinx.coroutines.delay
import org.springframework.stereotype.Service

@Service
class RequestService1 {

    suspend fun getData(id: String): String {
        delay(1000)
        return "$id ${RequestService1::class.java}"
    }
}
