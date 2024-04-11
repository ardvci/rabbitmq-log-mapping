package dev.ardvci
package dataGenerator

import io.circe.Json
import enums.{BookName, StoreLocation}
import scala.util.Random
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit.SECONDS
import java.util.concurrent.ThreadLocalRandom
import java.time.temporal.ChronoUnit


class BookOrderGenerator {
  private val bookNameList = BookName.values.toList
  private val StoreLocationList = StoreLocation.values.toList
  private val rand = new scala.util.Random


  private def createRandomDateTime(from: LocalDateTime = LocalDateTime.of(2023, 1, 1, 0, 0),
                                   to: LocalDateTime = LocalDateTime.of(2024, 1, 1, 0, 0)): String = {
    val diff = ChronoUnit.SECONDS.between(from, to)
    val randomSeconds = ThreadLocalRandom.current().nextLong(diff)
    from.plusSeconds(randomSeconds).toString
  }


  def generate(): Json = {
    val generatedFields = List(
      ("OrderDate", Json.fromString(createRandomDateTime())),
      ("StoreName", Json.fromString(StoreLocationList(rand.between(0, StoreLocationList.length)).toString)),
      ("bookName", Json.fromString(bookNameList(rand.between(0, bookNameList.length)).toString))
    )
    val jsonFromFields: Json = Json.fromFields(generatedFields)
    jsonFromFields

  }

}
