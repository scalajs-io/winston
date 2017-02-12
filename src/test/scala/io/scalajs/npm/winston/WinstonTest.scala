package io.scalajs.npm
package winston

import io.scalajs.nodejs.setTimeout
import io.scalajs.npm.winston.WinstonTest.MetaData
import io.scalajs.npm.winston.transports._
import org.scalatest.FunSpec

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined

/**
  * Winston Tests
  * @author lawrence.daniels@gmail.com
  */
class WinstonTest extends FunSpec {

  describe("Winston") {

    it("has a default logger") {
      Winston.log("info", "Hello distributed log files!")
      Winston.info("Hello again distributed logs")

      Winston.level = "debug"
      Winston.log("debug", "Now my debug messages are written to console!")
    }

    it("supports adding/removing transports") {
      Winston.add(File, new FileTransportOptions(filename = "somefile.log"))
      Winston.remove(Console)
    }

    it("can also configure transports via configure()") {
      Winston.configure(new ConfigurationOptions(
        transports = js.Array(new winston.transports.File(new FileTransportOptions(filename = "somefile.log")))
      ))
    }

    it("supports instantiating logger instances") {
      val logger = new winston.Logger(new ConfigurationOptions(
        transports = js.Array(
          new Console(),
          new File(new FileTransportOptions(filename = "somefile.log"))
        )))
    }

    it("supports multiple transports of the same type") {
      val logger = new winston.Logger(new ConfigurationOptions(
        transports = js.Array(
          new winston.transports.File(new FileTransportOptions(
            name = "info-file",
            filename = "filelog-info.log",
            level = "info"
          )),
          new winston.transports.File(new FileTransportOptions(
            name = "error-file",
            filename = "filelog-error.log",
            level = "error"
          ))
        )
      ))
    }

    it("supports profiling") {
      Winston.profile("test")

      setTimeout(() => {
        //
        // Stop profile of 'test'. Logging will now take place:
        //   "17 Jan 21:00:00 - info: test duration=1000ms"
        //
        Winston.profile("test")
      }, 1000)
    }

    it("supports string interpolation") {
      val logger = new winston.Logger(new ConfigurationOptions(
        transports = js.Array(new Console())
      ))

      logger.log("info", "test message %s", "my string")
      // info: test message my string

      logger.log("info", "test message %d", 123)
      // info: test message 123

      logger.log("info", "test message %j", new MetaData(number = 123), new MetaData())
      // info: test message {"number":123}
      // meta = {}

      logger.log("info", "test message %s, %s", "first", "second", new MetaData(number = 123))
      // info: test message first, second
      // meta = {number: 123}

      logger.log("info", "test message", "first", "second", new MetaData(number = 123))
      // info: test message first second
      // meta = {number: 123}

      logger.log("info", "test message %s, %s", "first", "second", new MetaData(number = 123), (() => {}): js.Function)
      // info: test message first, second
      // meta = {number: 123}
      // callback = function(){}

      logger.log("info", "test message", "first", "second", new MetaData(number = 123), (() => {}): js.Function)
      // info: test message first second
      // meta = {number: 123}
      // callback = function(){}
    }

  }

}

/**
  * Winston Test Singleton
  * @author lawrence.daniels@gmail.com
  */
object WinstonTest {

  @ScalaJSDefined
  class MetaData(val number: Integer = null) extends js.Object

}