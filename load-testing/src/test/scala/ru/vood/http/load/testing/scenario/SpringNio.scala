package ru.vood.http.load.testing.scenario

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder
import ru.vood.http.load.testing.scenario.Const.ids2feeder

object SpringNio {

  def req(path: String) = http(path)
    .get(path)
    .check(status is 200)

  private val httpProtocolBuilder: HttpProtocolBuilder = http.baseUrl("http://localhost:8080/")

  val builder: String => ScenarioBuilder = { path =>
    scenario(path) /*feed ids2feeder(1000, 1000)*/ exec req(path)
  }


  def scenarioData(path: String) = {
    OneScenarioData(
      builder(path),
      httpProtocolBuilder)
  }


  val callableScenarioData = scenarioData("callable/data")
  val deferredScenarioData = scenarioData("deferred/data")
  val hystrixCallableScenarioData = scenarioData("hystrix-callable/data")
  val hystrixScenarioData = scenarioData("hystrix/data")
  val observableDeferredScenarioData = scenarioData("observable-deferred/data")
  val observableScenarioData = scenarioData("observable/data")
  val syncScenarioData = scenarioData("sync/data")

}
