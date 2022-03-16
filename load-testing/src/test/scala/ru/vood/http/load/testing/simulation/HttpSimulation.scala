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


  private val scn: ScenarioBuilder = scenario("classicRestScenario")
    .exec(classicRestScenario)
    .exec(pause(10))

  private val coroutinesGetScenarioBuilder: ScenarioBuilder = scenario("coroutinesGetScenario").exec(coroutinesGetScenario).exec(pause(10))

  private val usersCnt = 500
  private val seconds = 30
//    setUp(
//      scn
//        .inject(constantUsersPerSec(usersCnt) during (seconds seconds))
//        .protocols(httpBuilder),
//      coroutinesGetScenarioBuilder
//        .inject(constantUsersPerSec(usersCnt) during (seconds seconds))
//        .protocols(httpBuilder)
//    )


 /* setUp(
    scn
      .inject(constantUsersPerSec(usersCnt) during (seconds seconds))
      .protocols(httpBuilder)
      .andThen(
      coroutinesGetScenarioBuilder
        .inject(constantUsersPerSec(usersCnt) during (seconds seconds))
        .protocols(httpBuilder)
    )
  )*/


  setUp(
    coroutinesGetScenarioBuilder
      .inject(constantUsersPerSec(usersCnt) during (seconds seconds))
      .protocols(httpBuilder)

      .andThen(
        scn
          .inject(constantUsersPerSec(usersCnt) during (seconds seconds))
          .protocols(httpBuilder)
      )
  )


}
