package net.plsk.bblkafka.streamGen

import java.time.ZonedDateTime

import akka.NotUsed
import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.Source
import org.slf4j.Logger

import scala.concurrent.ExecutionContextExecutor

trait AkkaStream[In] {

  implicit val system: ActorSystem = ActorSystem("kafka-gen")
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val ec: ExecutionContextExecutor = system.dispatcher

  def logger: Logger

  lazy val streamStartDate = ZonedDateTime.now

  def fromStream(stream: Stream[In]): Source[In, NotUsed] = {
    logger.debug(s"Start kafka-gen at $streamStartDate")
    Source(stream)
  }

  def terminate(msg: String): Unit = {
    logger.debug(msg)
    system.terminate()
  }
}
