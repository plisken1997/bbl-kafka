package net.plsk.bblkafka

import net.plsk.bblkafka.config.Config.{BootstrapServer, Topic}

package object config {

  object AppConfig {

    lazy val topic: Topic = pureconfig.loadConfigOrThrow[Topic]("topic")

    lazy val bootstrapServer: BootstrapServer = pureconfig.loadConfigOrThrow[BootstrapServer]("bootstrapServer")
  }

}
