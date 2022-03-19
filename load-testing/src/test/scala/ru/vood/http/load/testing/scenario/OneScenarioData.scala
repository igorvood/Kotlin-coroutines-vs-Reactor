package ru.vood.http.load.testing.scenario

import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.protocol.HttpProtocolBuilder
import io.gatling.http.request.builder.HttpRequestBuilder

case class OneScenarioData(
                        scenarioBuilder: ScenarioBuilder,
                        protocol: HttpProtocolBuilder
                      )

