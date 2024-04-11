package dev.ardvci

import configFactory.ConfigFactory
import rabbitmq.consumer.Consumer


object mainConsumer {
  def main(args: Array[String]): Unit = {
    val rabbitmqConfig = ConfigFactory().readYamlFile().get()
    Consumer(
      user = rabbitmqConfig.hcursor.downField("user").as[String].getOrElse(""),
      password = rabbitmqConfig.hcursor.downField("password").as[String].getOrElse(""),
      host = rabbitmqConfig.hcursor.downField("host").as[String].getOrElse(""),
      port = rabbitmqConfig.hcursor.downField("port").as[String].getOrElse("")

    ).consume()
  }
}