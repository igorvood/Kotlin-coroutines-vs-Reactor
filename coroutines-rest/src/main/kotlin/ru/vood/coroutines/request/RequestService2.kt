package ru.vood.coroutines.request

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import ru.vood.coroutines.constObj.Scope.crScope

@Service
class RequestService2(
    private val restTemplate: RestTemplate
) {

    @Value("\${delay}")
    var delayV: Long = 10

    @Value("\${RequestService2}")
    var hostPort = "localhost:8002"

    suspend fun getDataAsync(id: String): Deferred<String> =
        crScope.async {
            val forEntity = restTemplate.getForEntity(
                "http://$hostPort/externalFirstService/$id",
                String::class.java
            ).body!!
            "$id $forEntity"

        }
}
