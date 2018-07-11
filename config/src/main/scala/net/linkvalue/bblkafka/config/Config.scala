package net.linkvalue.bblkafka.config

object Config {

  case class Server(host: String, port: Int) {
    override def toString = s"$host:$port"
  }

  case class BootstrapServer(servers: Set[Server]) {
    override def toString = s"${servers.mkString(",")}"
  }

  case class Topic(u: String) extends AnyVal

}
