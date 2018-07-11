package net.plsk.bblkafka.kafkaProducer

import java.time.ZonedDateTime

import akka.Done
import akka.kafka.ProducerSettings
import akka.kafka.scaladsl.Producer
import net.plsk.bblkafka.config.AppConfig
import net.plsk.bblkafka.data.serDes.GenGameOpinion
import net.plsk.bblkafka.data.serDes.GenGameOpinion.GameOpinion
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.StringSerializer
import play.api.libs.json.Json
import net.plsk.bblkafka.data.serDes.json.JsonGameOpinionSerDes._
import net.plsk.bblkafka.streamGen.AkkaStream
import org.slf4j.LoggerFactory

import scala.concurrent.Future

object AkkaProducer extends App with AkkaStream[GameOpinion] {

  override val logger = LoggerFactory.getLogger(getClass)

  val config = system.settings.config.getConfig("akka.kafka.producer")
  val producerSettings: ProducerSettings[String, String] =
    ProducerSettings(config, new StringSerializer, new StringSerializer)
      .withBootstrapServers(AppConfig.bootstrapServer.toString)

  val elts = GenGameOpinion.streamGameOpinions()

  val done: Future[Done] = fromStream(elts)
    .map { elt =>
      val json = Json.toJson(elt)
      logger.debug(elt.game.name)
      logger.debug(json.toString)
      json.toString
    }
    .map(value => new ProducerRecord[String, String](AppConfig.topic.u, value))
    .runWith(Producer.plainSink(producerSettings))

  done.onComplete { _ =>
    val endDate = ZonedDateTime.now

    val duration: ZonedDateTime = endDate.minusSeconds(streamStartDate.getSecond)

    terminate(s"### End kafka-gen at $endDate - duration : ${duration.getSecond} seconds ###")
  }
}
