package dev.ardvci
package transformer

import dataGenerator.BookOrderInfoMapped
import enums.{BookName, StoreLocation}
import models.BookOrderInfo

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import scala.io.circe
import scala.io.circe.Json
import scala.io.circe.parser.decode
class BookOrderParser(val data: String){
}

object BookOrderParser {

  def generateJson(data: BookOrderInfoMapped): Json = {
    val generatedFields = List(
      ("OrderDate", Json.fromString(data.orderDate.toString)),
      ("StoreId", Json.fromInt(data.storeId)),
      ("BookId", Json.fromInt(data.bookId))
    )

    Json.fromFields(generatedFields)
  }


  def apply(data: String): Json = {
    val RawBookOrderData: Either[circe.Error, BookOrderInfo] = decode[BookOrderInfo](data)

    RawBookOrderData match {
      case Right(value) =>
        generateJson(BookOrderInfoMapped(
          orderDate = LocalDateTime.parse(value.orderDate, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")),
          storeId = StoreLocation.values.indexWhere(_.name == value.StoreName) + 1,
          bookId = BookName.values.indexWhere(_.name == value.bookName) + 1
        ))

      case Left(error) =>
        throw Exception("failed to parse the json: " + error)


    }

  }
}
