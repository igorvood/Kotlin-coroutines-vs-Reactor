package ru.vood.http.load.testing.scenario

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder


object ScenarioConst {

  private val webFluxRestScenario: HttpRequestBuilder = http("classicRestScenario")
    .get("webfluf/1")
    .check(jsonPath("$.data1") exists)
    .check(jsonPath("$.data2") exists)
    .check(status is 200)


  val webFluxScenarioData: OneScenarioData = OneScenarioData(
    scenario("webFluxRestScenario")
      .exec(webFluxRestScenario),
    http.baseUrl("http://localhost:8010/"))


  //  ----------------------------------------------------------

 private val classicRestScenario: HttpRequestBuilder = http("classicRestScenario")
    .get("classic/1")
    .check(jsonPath("$.data1") exists)
    .check(jsonPath("$.data2") exists)
    .check(status is 200)

  val classicRestScenarioData: OneScenarioData = OneScenarioData(
    scenario("classicRestScenario")
      .exec(classicRestScenario),
    http.baseUrl("http://localhost:8090/"))
  //  ----------------------------------------------------------

  val coroutinesGetScenario: HttpRequestBuilder = http("coroutinesGetScenario")
    .get("coroutine/1")

    .check(jsonPath("$.data1") exists)
    .check(jsonPath("$.data2") exists)
    //    .check(jsonPath("$.data2") notExists)
    .check(status is 200)

  //  ----------------------------------------------------------

  val notMyGetScenario: HttpRequestBuilder = http("notMyGetScenario")
    .get("1")

    .check(jsonPath("$.surname") exists)
    .check(status is 200)


}
