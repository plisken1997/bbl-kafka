package net.linkvalue.bblkafka.data.serDes

import org.scalacheck.Gen
import org.scalatest.prop.PropertyChecks

import scala.annotation.tailrec

object GenGameOpinion extends PropertyChecks {

  trait Category

  case object Fighting extends Category
  case object Action extends Category
  case object RPG extends Category
  case object TPS extends Category
  case object Sport extends Category
  case object FPS extends Category
  case object Strategy extends Category
  case object MMORPG extends Category
  case object PointAndClick extends Category

  trait Hardware

  case object PS4 extends Hardware
  case object PS3 extends Hardware
  case object PS2 extends Hardware
  case object PS1 extends Hardware
  case object PSP extends Hardware
  case object SNES extends Hardware
  case object Megadrive extends Hardware
  case object Saturn extends Hardware
  case object Dreamcast extends Hardware
  case object Xbox extends Hardware
  case object Xbox360 extends Hardware
  case object XboxOne extends Hardware
  case object SWITCH extends Hardware
  case object GAMECUBE extends Hardware
  case object PC extends Hardware

  trait Country
  case object JP extends Country
  case object FR extends Country
  case object US extends Country
  case object UK extends Country
  case object DE extends Country
  case object PL extends Country

  case class Brand(name: String, country: Country)

  case class Game(name: String, brand: Brand, category: Category, hardware: List[Hardware], year: Int)

  trait Source

  case object Twitter extends Source
  case object Facebook extends Source
  case object `4chan` extends Source
  case object Redit extends Source
  case object Forum extends Source

  case class User(name: String, age: Int, source: Source)

  case class Opinion(ranking: Float, comment: String, fromHardware: List[Hardware])

  case class GameOpinion(
      game: Game,
      user: User,
      opinion: Opinion
  )

  val capcom = Brand("capcom", JP)
  val nintendo = Brand("nintendo", JP)
  val squaresoft = Brand("squaresoft", JP)
  val sega = Brand("sega", JP)
  val epic = Brand("epic", US)
  val ea = Brand("EA", US)
  val konami = Brand("konami", JP)
  val netherrealm = Brand("netherrealm", US)
  val naughtydog = Brand("naughtydog", US)
  val ubisoft = Brand("ubisoft", FR)
  val `cd project` = Brand("cd project", PL)
  val `sony santa monica` = Brand("sony santa monica", US)
  val blizzard = Brand("sony santa monica", UK)
  val virginGames = Brand("virgin Games", UK)
  val thq = Brand("thq", US)
  val fromsoftware = Brand("from software", JP)
  val lucasart = Brand("lucas art", US)
  val idsoftware = Brand("id software", US)

  val games = List(
    Game("street fighter III.3", capcom, Fighting, List(PS4, PS3, PS2, Dreamcast), 1999),
    Game("street fighter III.2", capcom, Fighting, List(PS4, PS2, Dreamcast), 1998),
    Game("street fighter III", capcom, Fighting, List(PS4, PS2, Dreamcast), 1997),
    Game("street fighter alpha 3", capcom, Fighting, List(PS4, PS2, PS1), 1996),
    Game("street fighter alpha 2", capcom, Fighting, List(PS4, PS2, PS1, SNES), 1995),
    Game("street fighter alpha 1", capcom, Fighting, List(PS4, PS2, PS1, PSP), 1995),
    Game("street fighter 2", capcom, Fighting, List(PS4, PS2, PS1, SNES, Megadrive, PSP), 1991),
    Game("monster hunter world", capcom, Action, List(PS4, PC), 2018),
    Game("zelda breath of the wild", nintendo, Action, List(SWITCH), 2017),
    Game("mario odyssey", nintendo, Action, List(SWITCH), 2017),
    Game("mario sunshine", nintendo, Action, List(GAMECUBE), 2004),
    Game("final fantasy VI", squaresoft, RPG, List(SNES, PSP), 1995),
    Game("final fantasy VII", squaresoft, RPG, List(PS1, PS3, PSP, PC), 1996),
    Game("final fantasy X", squaresoft, RPG, List(PS4, PS3, PSP, PC), 2003),
    Game("sonic the hedgehog", sega, Action, List(Megadrive), 1991),
    Game("sonic the hedgehog 2", sega, Action, List(Megadrive), 1993),
    Game("sonic the hedgehog 3", sega, Action, List(Megadrive), 1994),
    Game("gears of war", epic, TPS, List(Xbox360, XboxOne), 2006),
    Game("gears of war 2", epic, TPS, List(Xbox360, PC), 2008),
    Game("gears of war 3", epic, TPS, List(Xbox360, PC), 2010),
    Game("gears of war 4", epic, TPS, List(XboxOne, PC), 2017),
    Game("Fifa 18", ea, Sport, List(XboxOne, Xbox360, PS4, PS3, SWITCH, PC), 2017),
    Game("NBA live 2018", ea, Sport, List(XboxOne, Xbox360, PS4, PS3, SWITCH), 2017),
    Game("Meta Gear solid", konami, Action, List(PS4, PS3, GAMECUBE, PS1, PSP), 1997),
    Game("Meta Gear solid 2", konami, Action, List(PS3, PS2, PSP), 2003),
    Game("Meta Gear solid 3", konami, Action, List(PS3, Xbox, PS2, PSP), 2005),
    Game("Meta Gear solid 4", konami, Action, List(PS3), 2008),
    Game("Meta Gear solid 5", konami, Action, List(PS3, PS4, Xbox360, XboxOne, PC), 2014),
    Game("Mortal Kombat 2", netherrealm, Action, List(SNES, Megadrive), 1995),
    Game("Mortal Kombat X", netherrealm, Fighting, List(XboxOne, PS4), 2014),
    Game("Mortal Kombat XL", netherrealm, Fighting, List(XboxOne, PS4, PC), 2015),
    Game("Mortal Kombat XL", netherrealm, Fighting, List(XboxOne, PS4), 2015),
    Game("Uncharted 3", naughtydog, Action, List(PS4, PS3), 2011),
    Game("Far cry", ubisoft, FPS, List(PS2, PC), 2001),
    Game("Far cry 2", ubisoft, FPS, List(Xbox, PC), 2008),
    Game("Far cry 3", ubisoft, FPS, List(PS3, Xbox360, PC), 2011),
    Game("Far cry 4", ubisoft, FPS, List(PS3, Xbox360, PC), 2011),
    Game("Far cry 5", ubisoft, FPS, List(PS4, XboxOne, PC), 2018),
    Game("Assassin's creed", ubisoft, Action, List(PS3, Xbox360, PC), 2007),
    Game("Les lapins crétins", ubisoft, Action, List(GAMECUBE, SWITCH), 2004),
    Game("the witcher", `cd project`, Action, List(Xbox360, PC), 2008),
    Game("god of war", `sony santa monica`, Action, List(PS2), 2006),
    Game("god of war 2", `sony santa monica`, Action, List(PS2), 2008),
    Game("god of war 3", `sony santa monica`, Action, List(PS3, PS4), 2010),
    Game("god of war 4", `sony santa monica`, Action, List(PS4), 2018),
    Game("World of warcraft", blizzard, MMORPG, List(PC), 2004),
    Game("warcraft 2", blizzard, Strategy, List(PC, Saturn, PS1), 1997),
    Game("Starcraft", blizzard, Strategy, List(PC), 1997),
    Game("Starcraft 2", blizzard, Strategy, List(PC), 2013),
    Game("Aladdin", virginGames, Action, List(Megadrive), 1994),
    Game("darksiders", thq, Action, List(PS3, Xbox360), 1994),
    Game("demon souls", fromsoftware, Action, List(PS3), 2008),
    Game("dark souls", fromsoftware, Action, List(PC, PS3, Xbox360), 2010),
    Game("sim city", ea, Strategy, List(PC), 1990),
    Game("day of the tentacle", fromsoftware, PointAndClick, List(PS4, PC, PSP), 1994),
    Game("sam & max", fromsoftware, PointAndClick, List(PC), 1993),
    Game("Doom", idsoftware, FPS, List(PC, SNES), 1992),
    Game("wolfenstein 3d", idsoftware, FPS, List(PC, SNES), 1990),
  )

  case class GenerationMaxAttemptReachedException(maxAttempts: Int)
      extends Exception(s"max attempt reached ($maxAttempts) !")

  val generationMaxAttempts = 5

  @tailrec
  final def createFixtures(generationMaxAttempt: Int = generationMaxAttempts): GameOpinion =
    if (generationMaxAttempt == 0)
      throw GenerationMaxAttemptReachedException(generationMaxAttempts)
    else {
      val gameOpinion: Option[GameOpinion] = for {
        game <- Gen.oneOf(games).sample
        userName <- Gen.oneOf("gégé", "laura", "raf du 69", "lili", "JS", "Joe", "Simone", "zzzz", "Lol").sample
        userAge <- Gen.oneOf(8 to 70).sample
        source <- Gen.oneOf(Twitter, Facebook, Redit, Forum, `4chan`).sample
        ranking <- Gen.oneOf(1 to 5).sample
        comment <- Gen
          .oneOf(
            "Donec non nulla eu nulla porta cursus vel eget leo. Vivamus a nisi enim. Nulla dignissim lacus orci, in convallis.",
            "Nulla ipsum elit, ullamcorper nec rutrum et, bibendum a nisi. Morbi non odio diam. Etiam auctor, eros rhoncus accumsan dictum.",
            "Maecenas elit nunc, blandit quis ultricies ut, dictum at eros. Quisque dui elit, rutrum non sapien at, placerat aliquet odio.",
            "Mauris non vestibulum justo. Praesent gravida orci ut odio accumsan rhoncus. In rutrum nisl eu odio sodales, eu lacinia nibh.",
            "Morbi nec faucibus ante, sed luctus erat. Integer dui metus, venenatis scelerisque tincidunt a, lacinia ac mi. Fusce blandit a.",
            "Quisque varius egestas lectus, non maximus libero rhoncus ut. Sed faucibus tortor ligula. Phasellus scelerisque ligula vel massa tempus varius."
          )
          .sample
        hardwares <- Gen
          .someOf(PS4,
                  PS3,
                  PS2,
                  PS1,
                  PSP,
                  SNES,
                  Megadrive,
                  Saturn,
                  Dreamcast,
                  Xbox,
                  Xbox360,
                  XboxOne,
                  SWITCH,
                  GAMECUBE,
                  PC)
          .sample
      } yield
        GameOpinion(
          game,
          User(userName, userAge, source),
          Opinion(ranking, comment, hardwares.toList)
        )
      if (gameOpinion.isDefined) gameOpinion.get
      else createFixtures(generationMaxAttempt - 1)
    }

  def generateFakeElements(gen: Int): List[GameOpinion] = (0 to gen).toList.map(_ => createFixtures())

  def streamGameOpinions(gen: Option[Int] = None): Stream[GameOpinion] =
    gen.fold(Stream.from(0).map { cpt =>
      createFixtures()
    }) { maxGen =>
      (0 to maxGen).toStream.map(_ => createFixtures())
    }
}
