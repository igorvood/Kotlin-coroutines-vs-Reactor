package ru.vood.http.load.testing.scenario

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder
import io.gatling.http.request.builder.HttpRequestBuilder
import ru.vood.http.load.testing.scenario.ScenarioConst.webFlux


object ScenarioConst {

//  http://localhost:8010/webfluf/1

  private val webFluxRestScenario: HttpRequestBuilder = http("classicRestScenario")
    .get("webfluf/1")
    .check(jsonPath("$.data1") exists)
    .check(jsonPath("$.data2") exists)
    .check(status is 200)

  private val webFlux: ScenarioBuilder = scenario("webFluxRestScenario")
    .exec(webFluxRestScenario)

  val webFluxScenarioData: OneScenarioData = OneScenarioData(webFlux, http.baseUrl("http://localhost:8010/"))

  val classicRestScenario: HttpRequestBuilder = http("classicRestScenario")
    .get("classic/1")
    .check(jsonPath("$.data1") exists)
    .check(jsonPath("$.data2") exists)
    .check(status is 200)


  val coroutinesGetScenario: HttpRequestBuilder = http("coroutinesGetScenario")
    .get("coroutine/1")

    .check(jsonPath("$.data1") exists)
    .check(jsonPath("$.data2") exists)
//    .check(jsonPath("$.data2") notExists)
    .check(status is 200)

  val notMyGetScenario: HttpRequestBuilder = http("notMyGetScenario")
    .get("1")

    .check(jsonPath("$.surname") exists)
    .check(status is 200)


}
