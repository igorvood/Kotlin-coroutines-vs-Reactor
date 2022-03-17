package ru.vood.webflux.request

interface Response {
}

data class NotFoundResponse(
    val error: String
): Response
