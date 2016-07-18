import android.Keys._
import android.Dependencies.{LibraryDependency, aar}

android.Plugin.androidBuild

platformTarget in Android := "android-23"

name := "macroid-starter"

scalaVersion := "2.11.8"

run <<= run in Android

resolvers ++= Seq(
  Resolver.sonatypeRepo("releases"),
  "jcenter" at "http://jcenter.bintray.com"
)

javacOptions ++= Seq("-source", "1.7", "-target", "1.7")
scalacOptions ++= Seq("-feature", "-deprecation", "-target:jvm-1.7")

libraryDependencies ++= Seq(
  aar("org.macroid" %% "macroid" % "2.0.0-M5"),
  aar("org.macroid" %% "macroid-viewable" % "2.0.0-M5"),
  aar("com.android.support" % "support-v4" % "24.0.0")
)

proguardScala in Android := true

proguardOptions in Android ++= Seq(
  "-ignorewarnings",
  "-keep class scala.Dynamic"
)
