package dev.ardvci
package models

import java.time.LocalDateTime

case class BookOrderInfoMapped(orderDate: LocalDateTime, storeId: Int, bookId: Int)
