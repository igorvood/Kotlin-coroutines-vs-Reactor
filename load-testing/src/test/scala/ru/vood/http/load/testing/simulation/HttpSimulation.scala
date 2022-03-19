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


  private val httpNotMy: HttpProtocolBuilder = http.baseUrl("http://localhost:7000/")

  private val notMyGetScenarioGetScenarioBuilder: ScenarioBuilder = scenario("notMyGetScenario").exec(notMyGetScenario)
  //    .exec(pause(10))

  private val usersCnt = 300
  private val seconds = 3

  private val minute = 3

  def runPoint(data: OneScenarioData, inj: OpenInjectionStep*): PopulationBuilder = data.scenarioBuilder.inject(inj).protocols(data.protocol)


  setUp(
    runPoint(classicRestScenarioData,
      rampUsersPerSec(200).to(usersCnt).during(minute.minutes),
      constantUsersPerSec(usersCnt) during (minute minutes)
    ),

    runPoint(webFluxScenarioData,
      rampUsersPerSec(200).to(usersCnt).during(minute.minutes),
      constantUsersPerSec(usersCnt) during (minute minutes)
    ),

    runPoint(coroutinesRestScenarioData,
      rampUsersPerSec(200).to(usersCnt).during(minute.minutes),
      constantUsersPerSec(usersCnt) during (minute minutes)
    )
  )

 /* setUp(
    runPoint(classicRestScenarioData,
      constantUsersPerSec(usersCnt) during (seconds seconds)
    ),

    runPoint(webFluxScenarioData,
      constantUsersPerSec(usersCnt) during (seconds seconds)
    ),

    runPoint(coroutinesRestScenarioData,
      constantUsersPerSec(usersCnt) during (seconds seconds)
    )
  )*/

}
