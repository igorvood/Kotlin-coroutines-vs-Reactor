package ru.vood.http.load.testing.simulation

import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef.http
import io.gatling.http.protocol.HttpProtocolBuilder
import ru.vood.http.load.testing.scenario.ScenarioConst.{classicRestScenario, coroutinesGetScenario}

import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

class HttpSimulation extends Simulation {

  private val httpBuilderClassic: HttpProtocolBuilder = http.baseUrl("http://localhost:8090/")
  private val httpBuilderCoroutine: HttpProtocolBuilder = http.baseUrl("http://localhost:8000/")


  private val scn: ScenarioBuilder = scenario("classicRestScenario")
    .exec(classicRestScenario)
  //    .exec(pause(10))

  private val coroutinesGetScenarioBuilder: ScenarioBuilder = scenario("coroutinesGetScenario").exec(coroutinesGetScenario)
  //    .exec(pause(10))

  private val usersCnt = 300
  private val seconds = 10
  /* setUp(
     scn
       .inject(constantUsersPerSec(usersCnt) during (seconds seconds))
       .protocols(httpBuilderClassic),
     coroutinesGetScenarioBuilder
       .inject(constantUsersPerSec(usersCnt) during (seconds seconds))
       .protocols(httpBuilderCoroutine)
   )


 setUp(
   scn
     .inject(constantUsersPerSec(usersCnt) during (seconds seconds))
     .protocols(httpBuilderClassic)
     .andThen(
     coroutinesGetScenarioBuilder
       .inject(constantUsersPerSec(usersCnt) during (seconds seconds))
       .protocols(httpBuilderCoroutine)
   )
 )*/


  setUp(
    /*coroutinesGetScenarioBuilder
      .inject(constantUsersPerSec(usersCnt) during (seconds seconds))
      .protocols(httpBuilderCoroutine)

      .andThen(*/
    scn
      .inject(constantUsersPerSec(usersCnt) during (seconds seconds))
   /*   .throttle(
        reachRps(usersCnt*2) in (10 seconds),
        holdFor(10 seconds),
//        holdFor(1 minutes),
        reachRps(usersCnt*3) in (10 seconds),
        holdFor(10 seconds),
        reachRps(usersCnt*4) in (10 seconds),
        holdFor(10 seconds),
        reachRps(usersCnt*5) in (10 seconds),
        holdFor(10 seconds),
        reachRps(usersCnt*6) in (10 seconds),
        holdFor(10 seconds),
        reachRps(usersCnt*7) in (10 seconds),
        holdFor(10 seconds),
      )*/
      .protocols(httpBuilderClassic)
    //      )
  )


}
