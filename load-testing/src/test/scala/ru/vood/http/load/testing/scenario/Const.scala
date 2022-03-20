package ru.vood.http.load.testing.scenario

import ru.tinkoff.gatling.feeders._

object Const {

  val localCustomerId = "local_customer_id"

  val ids: Int => Seq[String] = { cnt =>
    //    val strings = for (i <- 1 to cnt) yield (/*prefix + */222).toString
    val strings = for (i <- 1 to cnt) yield (i).toString
    strings
  }


  val ids2feeder: (Int, Int)=>IndexedSeq[Map[String, String]] = { (userCount, countSeconds) =>
    ids(userCount * countSeconds).toFeeder(localCustomerId)
  }

}
