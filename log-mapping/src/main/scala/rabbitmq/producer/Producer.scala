package dev.ardvci
package rabbitmq.producer

import com.rabbitmq.client.*

import scala.io.circe.Json

class Producer(user: String, password: String, host: String, port:String){
  def produceMessage(message: Json): Unit = {
    val factory = new ConnectionFactory()
    factory.setUri(s"amqp://$user:$password@$host:$port")
    val connection = factory.newConnection()
    val channel = connection.createChannel()

    val queueName = "log-aggregation-queue"
    channel.basicPublish("", queueName, null, message.toString.getBytes("UTF-8"))
    println(s"Sent '$message'")

    channel.close()
    connection.close()
  }
}