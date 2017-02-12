package io.scalajs.npm.winston.transports

import io.scalajs.npm.winston.Transport

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/**
  * Console Transport
  * @author lawrence.daniels@gmail.com
  */
@js.native
@JSImport("winston", "transports.Console")
class Console() extends Transport

@js.native
@JSImport("winston", "transports.Console")
object Console extends Transport