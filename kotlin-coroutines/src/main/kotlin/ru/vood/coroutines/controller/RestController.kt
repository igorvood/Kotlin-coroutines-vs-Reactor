package ru.vood.coroutines.controller

import io.micrometer.core.instrument.MeterRegistry
import kotlinx.coroutines.runBlocking
import org.springframework.scheduling.annotation.Async
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import ru.vood.coroutines.request.DataCollector
import ru.vood.coroutines.request.RequestService1
import ru.vood.coroutines.request.RequestService2
import java.time.LocalDateTime
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicInteger

@RestController
open class RestController(
    val requestService1: RequestService1,
    val requestService2: RequestService2,
    private val meterRegistry: MeterRegistry,
) {

    private val map: ConcurrentHashMap<Int, Int> = ConcurrentHashMap()
    private val prevKey: AtomicInteger = AtomicInteger(0)

    private val counter = meterRegistry.counter("letter.rps")

//    @Async("restProcessorExecutor")
    @GetMapping("classic/{id}")
    open//    @GetMapping("/collectInfo")
    fun collectInfo(@PathVariable id: Int): DataCollector {
        val runBlocking = runBlocking {
            counter.increment()

            val now = LocalDateTime.now().second
            synchronized(this) {
                if (prevKey.get() != now && map.size == 3) {
                    map.remove(prevKey.get())
                    prevKey.set(now)
                }
                val computeIfAbsent = map.computeIfAbsent(now) { 0 }.plus(1)
                map.put(now, computeIfAbsent + 1)
            }


//            val id = 2
            val data = requestService1.getDataAsync(id.toString())
            val data1 = requestService2.getDataAsync(id.toString())
            DataCollector(data.await(), data1.await())
        }
        return runBlocking
    }

    @GetMapping("classic/rate")
    open fun cnt(): Double = map.values.average()

}