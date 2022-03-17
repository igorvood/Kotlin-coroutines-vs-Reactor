package ru.vood.coroutines.request

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import ru.vood.coroutines.constObj.Scope.crScope

@Service
class RequestService1 {

    @Value("\${delay}")
    var delayV: Long = 10

    suspend fun getData(id: String) = crScope.async {
        delay(delayV)
        "$id ${RequestService1::class.java}"
    }
}
