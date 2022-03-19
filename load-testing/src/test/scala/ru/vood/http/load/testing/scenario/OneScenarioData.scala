package ru.vood.http.load.testing.scenario

import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.protocol.HttpProtocolBuilder

case class OneScenarioData(
                            scenarioBuilder: ScenarioBuilder,
                            protocol: HttpProtocolBuilder
                          )

