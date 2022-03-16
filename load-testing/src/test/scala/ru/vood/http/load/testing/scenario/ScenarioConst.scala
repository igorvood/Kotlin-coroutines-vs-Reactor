package ru.vood.http.load.testing.scenario

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder


object ScenarioConst {

  val classicRestScenario: HttpRequestBuilder = http("classicRestScenario")
    .get("classic/1")
    .check(status is 200)

  val coroutinesGetScenario: HttpRequestBuilder = http("coroutinesGetScenario")
    .get("coroutine/1")
    .check(status is 200)


}
