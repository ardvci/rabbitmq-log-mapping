package dev.ardvci
package models

import io.circe.Decoder

case class BookOrderInfo(orderDate: String, StoreName: String, bookName: String)

object BookOrderInfo {
  implicit val decoder: Decoder[BookOrderInfo] = Decoder.instance { h =>
    for {
      OrderDate <- h.get[String]("OrderDate")
      StoreName <- h.get[String]("StoreName")
      bookName <- h.get[String]("bookName")
    } yield BookOrderInfo(OrderDate, StoreName, bookName)

  }

}
