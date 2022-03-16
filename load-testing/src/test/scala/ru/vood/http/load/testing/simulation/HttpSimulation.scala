package ru.vood.http.load.testing.simulation

import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef.http
import io.gatling.http.protocol.HttpProtocolBuilder
import ru.vood.http.load.testing.scenario.ScenarioConst.{classicRestScenario, coroutinesGetScenario}

import scala.concurrent.duration.DurationInt

class HttpSimulation extends Simulation {

  private val httpBuilder: HttpProtocolBuilder = http.baseUrl("http://localhost:8080/")


  private val scn: ScenarioBuilder = scenario("coroutinesGet")
    .exec(classicRestScenario)

  private val coroutinesGetScenarioBuilder: ScenarioBuilder = scenario("coroutinesGetScenario").exec(coroutinesGetScenario)

  private val usersCnt = 50
  private val seconds = 70
  setUp(
    scn
      .inject(constantUsersPerSec(usersCnt) during (seconds seconds))
      .protocols(httpBuilder),
/*

    coroutinesGetScenarioBuilder
      .inject(constantUsersPerSec(usersCnt) during (seconds seconds))
      .protocols(httpBuilder)
*/
  )


}
