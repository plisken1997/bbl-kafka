package net.linkvalue.bblkafka.streamGen

import java.time.ZonedDateTime

import akka.NotUsed
import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.Source

import scala.concurrent.ExecutionContextExecutor

trait AkkaStream[In] {

  implicit val system: ActorSystem = ActorSystem("kafka-gen")
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val ec: ExecutionContextExecutor = system.dispatcher

  lazy val streamStartDate = ZonedDateTime.now

  def fromStream(stream: Stream[In]): Source[In, NotUsed] = {
    println(s"Start kafka-gen at $streamStartDate")
    Source(stream)
  }

  def terminate(msg: String): Unit = {
    println(msg)
    system.terminate()
  }
}
