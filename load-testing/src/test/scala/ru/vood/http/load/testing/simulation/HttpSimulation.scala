package ru.vood.http.load.testing.simulation

import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef.http
import io.gatling.http.protocol.HttpProtocolBuilder
import ru.vood.http.load.testing.scenario.ScenarioConst.{classicRestScenario, coroutinesGetScenario, notMyGetScenario, webFluxScenarioData}

import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

class HttpSimulation extends Simulation {

  private val httpBuilderClassic: HttpProtocolBuilder = http.baseUrl("http://localhost:8090/")
  private val httpBuilderCoroutine: HttpProtocolBuilder = http.baseUrl("http://localhost:8000/")

  private val httpNotMy: HttpProtocolBuilder = http.baseUrl("http://localhost:7000/")


  private val scn: ScenarioBuilder = scenario("classicRestScenario")
    .exec(classicRestScenario)

  private val coroutinesGetScenarioBuilder: ScenarioBuilder = scenario("coroutinesGetScenario").exec(coroutinesGetScenario)

  private val notMyGetScenarioGetScenarioBuilder: ScenarioBuilder = scenario("notMyGetScenario").exec(notMyGetScenario)
  //    .exec(pause(10))

  private val usersCnt = 400
  private val seconds = 3

  private val minute = 2

  setUp(webFluxScenarioData.scenarioBuilder
    .inject(rampUsersPerSec(200).to(usersCnt).during(minute.minutes),
      constantUsersPerSec(usersCnt) during (minute minutes)
    )

    .protocols(webFluxScenarioData.protocol)
  )
  /*setUp(
    scn
      .inject(constantUsersPerSec(usersCnt) during (seconds seconds))
      .protocols(httpBuilderClassic),
    coroutinesGetScenarioBuilder
      .inject(constantUsersPerSec(usersCnt) during (seconds seconds))
      .protocols(httpBuilderCoroutine)
  )*/

  /*setUp(
    scn
      .inject(constantUsersPerSec(usersCnt) during (seconds seconds))
      .protocols(httpBuilderClassic)
      .andThen(
      coroutinesGetScenarioBuilder
        .inject(constantUsersPerSec(usersCnt) during (seconds seconds))
        .protocols(httpBuilderCoroutine)
    )
  )*/


  /* setUp(
     scn
       .inject(rampUsersPerSec(100).to(usersCnt).during(minute.minutes))
       .protocols(httpBuilderClassic)
       .andThen(
         coroutinesGetScenarioBuilder
           .inject(rampUsersPerSec(100).to(usersCnt).during(minute.minutes))
           .protocols(httpBuilderCoroutine)
       )
   )*/
  /* setUp(
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
 */


  /* setUp(notMyGetScenarioGetScenarioBuilder.inject(rampUsersPerSec(100).to(usersCnt).during(minute.minutes)))
     .protocols(httpNotMy)*/
}
