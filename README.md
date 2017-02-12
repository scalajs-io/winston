Winston API for Scala.js
================================
This is a Scala.js type-safe binding for [winston](https://www.npmjs.com/package/winston)

A multi-transport async logging library for Node.js.

#### Build Dependencies

* [ScalaJs.io v0.3.x](https://github.com/scalajs-io/scalajs.io)
* [SBT v0.13.13](http://www.scala-sbt.org/download.html)

#### Build/publish the SDK locally

```bash
 $ sbt clean publish-local
```

#### Running the tests

Before running the tests the first time, you must ensure the npm packages are installed:

```bash
$ npm install
```

Then you can run the tests:

```bash
$ sbt test
```

#### Examples

##### Using the Default Logger

The default logger is accessible through the winston module directly. 
Any method that you could call on an instance of a logger is available on the default logger:

```scala
import io.scalajs.npm.winston._

Winston.log("info", "Hello distributed log files!")
Winston.info("Hello again distributed logs")

Winston.level = "debug"
Winston.log("debug", "Now my debug messages are written to console!")
```

By default, only the `Console` transport is set on the default logger. 
You can add or remove transports via the `add()` and `remove()` methods:

```scala
import io.scalajs.npm.winston
import io.scalajs.npm.winston._
import io.scalajs.npm.winston.transports._

Winston.add(winston.transports.File, new FileTransportOptions(filename = "somefile.log"))
Winston.remove(winston.transports.Console)
```

Or do it with one call to configure():

```scala
import io.scalajs.npm.winston
import io.scalajs.npm.winston._
import io.scalajs.npm.winston.transports._
import scalajs.js

Winston.configure(new ConfigurationOptions(
  transports = js.Array(new winston.transports.File(new FileTransportOptions(filename = "somefile.log")))
))
```

##### Instantiating your own Logger

If you would prefer to manage the object lifetime of loggers you are free to instantiate them yourself:

```scala
import io.scalajs.npm.winston
import io.scalajs.npm.winston._
import io.scalajs.npm.winston.transports._
import scalajs.js

val logger = new winston.Logger(new ConfigurationOptions(
    transports = js.Array(
      new Console(),
      new File(new FileTransportOptions(filename = "somefile.log"))
)))
```

##### Multiple transports of the same type

It is possible to use multiple transports of the same type e.g. winston.transports.File by passing in 
a custom name when you construct the transport.

```scala
import io.scalajs.npm.winston
import io.scalajs.npm.winston._
import io.scalajs.npm.winston.transports._
import scalajs.js

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

// If you later want to remove one of these transports you can do so by using the string name. e.g.:

logger.remove("info-file")

// In this example one could also remove by passing in the instance of the Transport itself. 
// e.g. this is equivalent to the string example above

// Notice it was first in the Array above 
val infoFile = logger.transports(0)
logger.remove(infoFile)
```

##### Profiling

In addition to logging messages and metadata, winston also has a simple profiling mechanism implemented for any logger:

```scala
import io.scalajs.nodejs.setTimeout
import io.scalajs.npm.winston._

// 
// Start profile of 'test' 
// Remark: Consider using Date.now() with async operations 
// 
Winston.profile("test")

setTimeout(() => {
    // 
    // Stop profile of 'test'. Logging will now take place: 
    //   "17 Jan 21:00:00 - info: test duration=1000ms" 
    // 
    Winston.profile("test")
}, 1000)
```

##### String interpolation

The log method provides the same string interpolation methods like util.format.

This allows for the following log messages.

```scala
import io.scalajs.npm.winston
import io.scalajs.npm.winston._
import io.scalajs.npm.winston.transports._
import scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined

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

@ScalaJSDefined
class MetaData(val number: Integer = null) extends js.Object
```

#### Artifacts and Resolvers

To add the `Winston` binding to your project, add the following to your build.sbt:  

```sbt
libraryDependencies += "io.scalajs.npm" %%% "winston" % "2.3.1"
```

Optionally, you may add the Sonatype Repository resolver:

```sbt   
resolvers += Resolver.sonatypeRepo("releases") 
```