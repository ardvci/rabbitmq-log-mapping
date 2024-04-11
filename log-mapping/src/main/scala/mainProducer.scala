package dev.ardvci

import configFactory.ConfigFactory
import dataGenerator.BookOrderGenerator
import rabbitmq.producer.Producer

import scala.io.circe.Json

object mainProducer {
  def main(args: Array[String]): Unit = {
    val rabbitmqConfig = ConfigFactory().readYamlFile().get()
    while true do
      val data: Json = BookOrderGenerator().generate()
      Producer(
        user = rabbitmqConfig.hcursor.downField("user").as[String].getOrElse(""),
        password = rabbitmqConfig.hcursor.downField("password").as[String].getOrElse(""),
        host = rabbitmqConfig.hcursor.downField("host").as[String].getOrElse(""),
        port = rabbitmqConfig.hcursor.downField("port").as[String].getOrElse("")
      ).produceMessage(data)

  }
}
