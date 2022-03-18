package ru.vood.coroutines.request

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import ru.vood.coroutines.constObj.Scope.crScope

@Service
class RequestService1(
    private val restTemplate: RestTemplate) {

    @Value("\${delay}")
    var delayV: Long = 10

    suspend fun getDataAsync(id: String): Deferred<String> =
    crScope.async {
        val forEntity = restTemplate.getForEntity("http://localhost:8001//externalFirstService/$id", String::class.java).body!!
        "$id $forEntity"
    }
}
