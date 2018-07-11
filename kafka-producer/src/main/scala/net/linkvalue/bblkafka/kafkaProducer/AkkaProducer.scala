package net.linkvalue.bblkafka.kafkaProducer

import net.linkvalue.bblkafka.config._
import java.time.ZonedDateTime

import akka.Done
import akka.kafka.ProducerSettings
import akka.kafka.scaladsl.Producer
import net.linkvalue.bblkafka.data.serDes.GenGameOpinion
import net.linkvalue.bblkafka.data.serDes.GenGameOpinion.GameOpinion
import net.linkvalue.bblkafka.streamGen.AkkaStream
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.StringSerializer
import play.api.libs.json.Json
import net.linkvalue.bblkafka.data.serDes.json.JsonGameOpinionSerDes._

import scala.concurrent.Future

object AkkaProducer extends App with AkkaStream[GameOpinion] {

  val config = system.settings.config.getConfig("akka.kafka.producer")
  val producerSettings: ProducerSettings[String, String] =
    ProducerSettings(config, new StringSerializer, new StringSerializer)
      .withBootstrapServers(AppConfig.bootstrapServer.toString)

  val elts = GenGameOpinion.streamGameOpinions()

  val done: Future[Done] = fromStream(elts)
    .map { elt =>
      val json = Json.toJson(elt)
      println(elt.game.name)
      println(json.toString)
      json.toString
    }
    .map(value => new ProducerRecord[String, String](AppConfig.topic.u, value))
    //.runWith(Sink.seq)
    .runWith(Producer.plainSink(producerSettings))

  done.onComplete { _ =>
    val endDate = ZonedDateTime.now

    val duration: ZonedDateTime = endDate.minusSeconds(streamStartDate.getSecond)

    terminate(s"### End kafka-gen at $endDate - duration : ${duration.getSecond} seconds ###")
  }
}
