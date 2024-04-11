package dev.ardvci
package configFactory

import io.circe
import scala.io.Source
import io.circe.yaml.v12.parser
import io.circe.{Decoder, Error, Json, yaml}
import io.circe.parser.decode
import io.circe.generic.auto.deriveDecoder

import java.io.FileNotFoundException
import java.util.Optional

case class RabbitMQConfig(user: String, password: String, host: String, port: Int)
case class Services(rabbitmq: Json)

class ConfigFactory {
  def readYamlFile(): Optional[Json] = {
    try {
      val mappingSource = Source.fromFile("src/main/resources/application.yml")
      val mappingData = mappingSource.mkString
      mappingSource.close()

      val mappingJson = parser.parse(mappingData)

      val mappingServices = decode[Services](mappingJson.getOrElse(Json.Null).toString)
      mappingServices match {
        case Left(error) => Optional.empty()
        case Right(service) =>
          val mappings = service.rabbitmq
          Optional.of(mappings)
      }
    } catch {
      case _: FileNotFoundException => Optional.empty()
      case e: Throwable => Optional.empty()
    }
  }


}
