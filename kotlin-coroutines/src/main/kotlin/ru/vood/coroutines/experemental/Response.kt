package ru.vood.coroutines.experemental

interface Response {
}

data class NotFoundResponse(
    val error: String
): Response
