package ru.vood.coroutines.request

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import ru.vood.coroutines.constObj.Scope.crScope
import java.time.LocalDateTime
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicInteger

@Component
class RunTest(val restTemplate: RestTemplate) : CommandLineRunner {

    var idx = 0

    @Value("\${coroutineRest}")
    var hostPort: String = ""

    var prevSec: Optional<Int> = Optional.empty()

    val reqPerSec = 200

    val seconds = 60

    val ok =AtomicInteger(0)
    val error =AtomicInteger(0)

    val concurrentHashMap = ConcurrentHashMap<Int, Pair<AtomicInteger, AtomicInteger>>()

    override fun run(vararg args: String?) {


        while (idx < seconds) {
            val now = LocalDateTime.now()

            val second = now.second

            prevSec = if (!prevSec.isEmpty) prevSec else Optional.of(second)
            val or = prevSec.flatMap {prv-> if(prv != second) Optional.of(second) else Optional.empty() }



             or
                .map {
                    idx += 1
                    concurrentHashMap[second] = Pair(ok, error)
                    IntRange(1, reqPerSec).map {
                        crScope.async {
                            try {
                                val s = "http://$hostPort/classic/${now.nano}"
//                                val s = "http://$hostPort/coroutine/${now.nano}"
                                restTemplate.getForEntity(
                                    s,
                                    String::class.java
                                ).body!!
                                ok.incrementAndGet()
                            } catch (e: Exception){
                                println(e.message)
                                error.incrementAndGet()
                            }
                        }
                    }
                    second
                }

            prevSec = prevSec.map { prv -> if(prv == second) prv else second }
        }
        Thread.sleep(10000)
        println("""ok = ${ok.get()}""")
        println("""error = ${error.get()}""")

        println(concurrentHashMap)

    }
}