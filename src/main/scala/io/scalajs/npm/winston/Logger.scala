package io.scalajs.npm.winston

import io.scalajs.npm.winston.transports.FileTransportOptions

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/**
  * Winston Logger
  * @author lawrence.daniels@gmail.com
  */
@js.native
@JSImport("winston", "Logger")
class Logger(options: ConfigurationOptions) extends js.Object {

  ///////////////////////////////////////////////////////////////////////////////////////////
  //        Constructor
  ///////////////////////////////////////////////////////////////////////////////////////////

  protected def this() = this(js.native)

  ///////////////////////////////////////////////////////////////////////////////////////////
  //        Properties
  ///////////////////////////////////////////////////////////////////////////////////////////

  var level: String = js.native

  def transports: js.Array[Transport] = js.native

  ///////////////////////////////////////////////////////////////////////////////////////////
  //        Methods
  ///////////////////////////////////////////////////////////////////////////////////////////

  def add(transport: Transport, options: FileTransportOptions = js.native): Unit = js.native

  def configure(options: ConfigurationOptions): Unit = js.native

  def debug(message: String): Unit = js.native

  def debug(level: String, message: String, metadata: js.Any*): Unit = js.native

  def error(message: String): Unit = js.native

  def error(level: String, message: String, args: js.Any*): Unit = js.native

  def info(message: String): Unit = js.native

  def info(level: String, message: String, args: js.Any*): Unit = js.native

  def log(message: String): Unit = js.native

  def log(level: String, message: String, args: js.Any*): Unit = js.native

  def profile(name: String): Unit = js.native

  def remove(name: String): Unit = js.native

  def remove(transport: Transport): Unit = js.native

  def warn(message: String): Unit = js.native

  def warn(level: String, message: String, args: js.Any*): Unit = js.native

}
