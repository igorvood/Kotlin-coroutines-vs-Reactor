package ru.vood.webflux.request

import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

object Scope {
    private val job: CompletableJob = SupervisorJob()
    val crScope: CoroutineScope = CoroutineScope(Dispatchers.IO + job)

}