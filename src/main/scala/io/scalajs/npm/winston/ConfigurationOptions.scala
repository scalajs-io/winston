package io.scalajs.npm.winston

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined

/**
  * Configuration Options
  */
@ScalaJSDefined
class ConfigurationOptions(val transports: js.UndefOr[js.Array[_ <: Transport]] = js.undefined) extends js.Object