package net.linkvalue.bblkafka.kafkaConsumer

import net.linkvalue.bblkafka.config._
import akka.kafka.{ConsumerSettings, Subscriptions}
import akka.kafka.scaladsl.Consumer
import akka.stream.scaladsl.Sink
import net.linkvalue.bblkafka.data.serDes.GenGameOpinion.GameOpinion
import net.linkvalue.bblkafka.streamGen.AkkaStream
import org.apache.kafka.clients.consumer.{ConsumerConfig, ConsumerRecord}
import org.apache.kafka.common.serialization.StringDeserializer
import net.linkvalue.bblkafka.data.serDes.json.JsonGameOpinionSerDes._
import play.api.libs.json.{JsError, JsSuccess, Json}

object AkkaConsumer extends App with AkkaStream[GameOpinion] {

  val consumerGroupId = "game-opinion-reader"

  val config = system.settings.config.getConfig("akka.kafka.consumer")
  val consumerSettings =
    ConsumerSettings(config, new StringDeserializer, new StringDeserializer)
      .withBootstrapServers(AppConfig.bootstrapServer.toString)
      .withGroupId(consumerGroupId)
      .withProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest")
      .withProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true")
      .withProperty(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "5000")

  Consumer
    .plainSource(
      consumerSettings,
      Subscriptions.topics(AppConfig.topic.u)
    )
    .map { res: ConsumerRecord[String, String] =>
      Json.parse(res.value()).validate[GameOpinion] match {
        case JsSuccess(v, _) =>
          println(v.game.name)
        case JsError(err) =>
          println("pas cool")
      }
    }
    .runWith(Sink.seq)

}
