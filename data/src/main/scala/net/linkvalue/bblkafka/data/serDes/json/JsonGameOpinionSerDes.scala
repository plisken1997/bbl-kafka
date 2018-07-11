package net.linkvalue.bblkafka.data.serDes.json

import net.linkvalue.bblkafka.data.serDes.GenGameOpinion._
import play.api.libs.json._
import io.leonard.TraitFormat.{caseObjectFormat, traitFormat}

object JsonGameOpinionSerDes {

  implicit val sourceFormat = traitFormat[Source] << caseObjectFormat(Twitter) << caseObjectFormat(Facebook) << caseObjectFormat(
    `4chan`) << caseObjectFormat(Redit) << caseObjectFormat(Forum)

  implicit val categoryFormat = traitFormat[Category] << caseObjectFormat(Fighting) << caseObjectFormat(Action) << caseObjectFormat(
    RPG) << caseObjectFormat(TPS) << caseObjectFormat(Sport) << caseObjectFormat(FPS) << caseObjectFormat(Strategy) << caseObjectFormat(
    MMORPG) << caseObjectFormat(PointAndClick)

  implicit val hardwareFormat = traitFormat[Hardware] << caseObjectFormat(PS4) << caseObjectFormat(PS3) << caseObjectFormat(
    PS2) << caseObjectFormat(PS1) << caseObjectFormat(PSP) << caseObjectFormat(SNES) << caseObjectFormat(Megadrive) << caseObjectFormat(
    Saturn) << caseObjectFormat(Dreamcast) << caseObjectFormat(Xbox) << caseObjectFormat(Xbox360) << caseObjectFormat(
    XboxOne) << caseObjectFormat(SWITCH) << caseObjectFormat(GAMECUBE) << caseObjectFormat(PC)

  implicit val countryFormat = traitFormat[Country] << caseObjectFormat(JP) << caseObjectFormat(FR) << caseObjectFormat(
    US) << caseObjectFormat(UK) << caseObjectFormat(DE) << caseObjectFormat(PL)

  implicit val brandFormat = Json.format[Brand]

  implicit val gameFormat = Json.format[Game]

  implicit val userFormat = Json.format[User]

  implicit val opinionFormat = Json.format[Opinion]

  implicit val gameOpinionFormat = Json.format[GameOpinion]
}
