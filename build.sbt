name := "scalaz-stream-test"

organization := "com.oomatomo.scalaz.stream.test"

version := "0.0.1"

scalaVersion := "2.11.2"

libraryDependencies ++= Seq(
  "org.specs2" %% "specs2" % "2.4" % "test"
  , "org.scalaz.stream" %% "scalaz-stream" % "0.7a"
)

scalacOptions in Test ++= Seq("-Yrangepos")

// Read here for optional dependencies:
// http://etorreborre.github.io/specs2/guide/org.specs2.guide.Runners.html#Dependencies

resolvers ++= Seq("snapshots", "releases").map(Resolver.sonatypeRepo)
resolvers += "Scalaz Bintray Repo" at "https://dl.bintray.com/scalaz/releases"

initialCommands := "import com.oomatomo.scalaz.stream.test.scalazstreamtest._"
