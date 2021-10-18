import play.sbt.routes.RoutesKeys

name := """java-play-init"""
version := "1.0"
scalaVersion := "2.13.6"
crossScalaVersions := Seq("2.12.10")

lazy val root = (project in file(".")).enablePlugins(PlayJava)

// Necessário para obter o nome das variáveis dos métodos via reflection
javacOptions ++= Seq("-parameters")

// Google Guice ( https://github.com/google/guice/wiki/Motivation )
libraryDependencies += guice
libraryDependencies += "com.google.inject.extensions" % "guice-multibindings" % "4.2.3"

// WebService...
libraryDependencies += ws


// https://github.com/typesafehub/config
libraryDependencies += "com.typesafe" % "config" % "1.4.0"

// Testing libraries for dealing with CompletionStage...
libraryDependencies += "org.assertj" % "assertj-core" % "3.6.2" % Test
libraryDependencies += "org.awaitility" % "awaitility" % "2.0.0" % Test

// Apache Commons ( https://commons.apache.org/ )
libraryDependencies += "org.apache.commons" % "commons-compress" % "1.21"
libraryDependencies += "org.apache.commons" % "commons-lang3" % "3.12.0"
libraryDependencies += "org.apache.commons" % "commons-collections4" % "4.4"

// Vavr - Biblioteca funcional de objetos para Java 8+  ( https://www.vavr.io/ )
libraryDependencies += "io.vavr" % "vavr" % "0.10.4"

// Caelum Stella ( https://github.com/caelum/caelum-stella/wiki )
libraryDependencies += "br.com.caelum.stella" % "caelum-stella-core" % "2.1.5"

// Make verbose tests
testOptions in Test := Seq(Tests.Argument(TestFrameworks.JUnit, "-a", "-v"))

// A partir da versao 2.4 do Play eh necessário setar essa opção para carregar no classpath o conteúdo da
// pasta "conf" no momento do deploy da aplicação.
PlayKeys.externalizeResources := false

// Setando provedor padrão.
PlayKeys.devSettings += "play.server.provider" -> "play.core.server.AkkaHttpServerProvider"

// Disable documentation
// https://www.playframework.com/documentation/2.4.x/Production
// Desativado a geração de documentação na fase de deploy para acelerar a compilação.
sources in (Compile, doc) := Seq.empty
publishArtifact in (Compile, packageDoc) := false

// Restringir log.
logLevel := Level.Error