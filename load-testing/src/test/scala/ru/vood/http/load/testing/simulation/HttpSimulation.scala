package ru.vood.http.load.testing.simulation

import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef.http
import io.gatling.http.protocol.HttpProtocolBuilder
import ru.vood.http.load.testing.scenario.ScenarioConst.coroutinesGetScenario

import scala.concurrent.duration.DurationInt

class HttpSimulation extends Simulation {

  private val httpBuilder: HttpProtocolBuilder = http.baseUrl("http://localhost:8080/")


  private val scn: ScenarioBuilder = scenario("asdas")
    .exec(coroutinesGetScenario)

  setUp(
    scn
      .inject(constantUsersPerSec(10) during (2 seconds))
      .protocols(httpBuilder))


}