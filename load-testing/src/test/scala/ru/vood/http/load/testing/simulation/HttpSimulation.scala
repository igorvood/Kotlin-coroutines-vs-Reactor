package ru.vood.http.load.testing.simulation

import io.gatling.core.Predef._
import io.gatling.core.controller.inject.open.OpenInjectionStep
import io.gatling.core.scenario.Simulation
import io.gatling.core.structure.{PopulationBuilder, ScenarioBuilder}
import io.gatling.http.Predef.http
import io.gatling.http.protocol.HttpProtocolBuilder
import ru.vood.http.load.testing.scenario.OneScenarioData
import ru.vood.http.load.testing.scenario.ScenarioConst._

import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

class HttpSimulation extends Simulation {

  private val httpBuilderClassic: HttpProtocolBuilder = http.baseUrl("http://localhost:8090/")
  private val httpBuilderCoroutine: HttpProtocolBuilder = http.baseUrl("http://localhost:8000/")

  private val httpNotMy: HttpProtocolBuilder = http.baseUrl("http://localhost:7000/")


  private val coroutinesGetScenarioBuilder: ScenarioBuilder = scenario("coroutinesGetScenario").exec(coroutinesGetScenario)

  private val notMyGetScenarioGetScenarioBuilder: ScenarioBuilder = scenario("notMyGetScenario").exec(notMyGetScenario)
  //    .exec(pause(10))

  private val usersCnt = 2100
  private val seconds = 3

  private val minute = 2

  def runPoint(data: OneScenarioData, inj: OpenInjectionStep*): PopulationBuilder = data.scenarioBuilder.inject(inj).protocols(data.protocol)


  setUp(runPoint(classicRestScenarioData,
    rampUsersPerSec(200).to(usersCnt).during(minute.seconds),
    constantUsersPerSec(usersCnt) during (minute seconds)
  )
    .andThen(
      runPoint(webFluxScenarioData,
        rampUsersPerSec(200).to(usersCnt).during(minute.seconds),
        constantUsersPerSec(usersCnt) during (minute seconds)
      )
    )
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
