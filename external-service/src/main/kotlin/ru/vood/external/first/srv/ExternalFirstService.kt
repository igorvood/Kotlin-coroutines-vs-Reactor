package ru.vood.external.first.srv

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Async
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
open class ExternalFirstService {

    @Value("\${delay}")
    var delayV: Long = 10

    val logger = LoggerFactory.getLogger(ExternalFirstService::class.java)

//    @Async("restProcessorExecutor")
    @GetMapping(value = ["externalFirstService/{id}"],produces=["application/json"])
    @ResponseBody
    open fun collectInfo(@PathVariable id: String): OutValue {
        if (delayV > 0) {
            Thread.sleep(delayV)
        }
        val s = "$id ${ExternalFirstService::class.java}"
//        logger.info(s)
        return OutValue(s)
    }

}
