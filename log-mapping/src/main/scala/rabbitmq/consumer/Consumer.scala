package dev.ardvci
package rabbitmq.consumer

import transformer.BookOrderParser

import com.rabbitmq.client.*


class Consumer(user: String, password: String, host:String, port: String) {
  def consume(): Unit = {
    val factory = new ConnectionFactory()
    factory.setUri(s"amqp://$user:$password@$host:$port")

    val connection = factory.newConnection()
    val channel = connection.createChannel()

    val queueName = "log-aggregation-queue"
    channel.queueDeclare(queueName, true, false, false, null)

    println("Waiting for messages. To exit press CTRL+C")

    val consumer = new DefaultConsumer(channel) {
      override def handleDelivery(consumerTag: String,
                                  envelope: Envelope,
                                  properties: AMQP.BasicProperties,
                                  body: Array[Byte]): Unit = {
        try {
          val message = new String(body, "UTF-8")
          val data = BookOrderParser(message)
          println(data)
        }
        catch
          case _ => println("Parsing problem")

      }
    }

    channel.basicConsume(queueName, true, consumer)
  }
}