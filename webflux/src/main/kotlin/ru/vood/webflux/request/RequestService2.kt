package ru.vood.webflux.request

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Service
class RequestService2(
    @Qualifier("externalSecondService") private val restTemplate: WebClient
) {

    fun getDataAsync(id: String): Mono<String> {
        val accept = restTemplate.get().uri("/{id}", id)
            .retrieve()
            .bodyToMono(String::class.java)
        return accept
    }
}
