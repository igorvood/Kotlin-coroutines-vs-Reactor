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

  private val usersCnt = 150
  private val seconds = 3

  private val minute = 1800

  def runPoint(data: OneScenarioData, inj: OpenInjectionStep*): PopulationBuilder = data.scenarioBuilder.inject(inj).protocols(data.protocol)


  setUp(
    runPoint(classicRestScenarioData,
      rampUsersPerSec(50).to(usersCnt).during(minute.seconds),
      constantUsersPerSec(usersCnt) during (minute seconds)
    ).andThen(
      runPoint(webFluxScenarioData,
        rampUsersPerSec(50).to(usersCnt).during(minute.seconds),
        constantUsersPerSec(usersCnt) during (minute seconds)
      ).andThen(
        runPoint(coroutinesRestScenarioData,
          rampUsersPerSec(50).to(usersCnt).during(minute.seconds),
          constantUsersPerSec(usersCnt) during (minute seconds)
        )
      )
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

  /*
  *  val callableScenarioData= scenarioData("callable/data")
    val deferredScenarioData= scenarioData("deferred/data")
    val hystrixCallableScenarioData= scenarioData("hystrix-callable/data")
    val hystrixScenarioData= scenarioData("hystrix/data")
    val observableDeferredScenarioData= scenarioData("observable-deferred/data")
    val observableScenarioData= scenarioData("observable/data")
    val syncScenarioData= scenarioData("sync/data")
  */

  /*setUp(
    runPoint(callableScenarioData,
      rampUsersPerSec(50).to(usersCnt).during(minute.seconds),
      constantUsersPerSec(usersCnt) during (minute seconds)
    ).andThen(
      runPoint(deferredScenarioData,
        rampUsersPerSec(50).to(usersCnt).during(minute.seconds),
        constantUsersPerSec(usersCnt) during (minute seconds)
      )))*/

  /*setUp(
    runPoint(callableScenarioData,
      rampUsersPerSec(200).to(usersCnt).during(minute.seconds),
      constantUsersPerSec(usersCnt) during (minute seconds)
    ).andThen(
      runPoint(deferredScenarioData,
        rampUsersPerSec(200).to(usersCnt).during(minute.seconds),
        constantUsersPerSec(usersCnt) during (minute seconds)
      ).andThen(
        runPoint(hystrixCallableScenarioData,
          rampUsersPerSec(200).to(usersCnt).during(minute.seconds),
          constantUsersPerSec(usersCnt) during (minute seconds)
        ).andThen(
          runPoint(hystrixScenarioData,
            rampUsersPerSec(200).to(usersCnt).during(minute.seconds),
            constantUsersPerSec(usersCnt) during (minute seconds)
          ).andThen(
            runPoint(observableDeferredScenarioData,
              rampUsersPerSec(200).to(usersCnt).during(minute.seconds),
              constantUsersPerSec(usersCnt) during (minute seconds)
            ).andThen(
              runPoint(observableScenarioData,
                rampUsersPerSec(200).to(usersCnt).during(minute.seconds),
                constantUsersPerSec(usersCnt) during (minute seconds)
              ).andThen(
                runPoint(syncScenarioData,
                  rampUsersPerSec(200).to(usersCnt).during(minute.seconds),
                  constantUsersPerSec(usersCnt) during (minute seconds)
                )

              ))))))
  )*/

}
