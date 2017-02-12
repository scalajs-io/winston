package io.scalajs.npm.winston.transports

import io.scalajs.npm.winston.Transport

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSImport, ScalaJSDefined}

/**
  * File Transport
  * @author lawrence.daniels@gmail.com
  */
@js.native
@JSImport("winston", "transports.File")
class File(options: FileTransportOptions) extends Transport

/**
  * File Transport Singleton
  * @author lawrence.daniels@gmail.com
  */
@js.native
@JSImport("winston", "transports.File")
object File extends Transport

/**
  * Winston Transport Options
  * @param filename the name of the file to log to
  */
@ScalaJSDefined
class FileTransportOptions(val name: js.UndefOr[String] = js.undefined,
                           val filename: js.UndefOr[String] = js.undefined,
                           val level: js.UndefOr[String] = js.undefined) extends js.Object