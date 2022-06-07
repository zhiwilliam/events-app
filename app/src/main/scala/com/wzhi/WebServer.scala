package com.wzhi

import zio._
import zio.json._
import java.net.http._
import zhttp.http._
import zhttp.service.Server

object WebServer extends ZIOAppDefault {
  val register = Http.collect[Request] {
    case Method.GET -> !! / "user" / name / "greet" =>
      Response.text(s"Welcome to hello world $name!")
  }

  def run = Server.start(9090, register).exitCode
}