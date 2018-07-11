package net.linkvalue.bblkafka

import net.linkvalue.bblkafka.config.Config.{BootstrapServer, Topic}

package object config {

  object AppConfig {

    lazy val topic: Topic = pureconfig.loadConfigOrThrow[Topic]("topic")

    lazy val bootstrapServer: BootstrapServer = pureconfig.loadConfigOrThrow[BootstrapServer]("bootstrapServer")
  }

}
