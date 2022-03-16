package ru.vood.http.load.testing.scenario

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder


object ScenarioConst {

  val coroutinesGetScenario: HttpRequestBuilder = http("coroutinesGet")
    .get("classic/1")
    .check(status is 200)


}
