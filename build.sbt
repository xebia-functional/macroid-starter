import android.Keys._

android.Plugin.androidBuild

platformTarget in Android := "android-17"

name := "macroid-starter"

scalaVersion := "2.10.2"

resolvers ++= Seq(
  "jrebel" at "http://jcenter.bintray.com",
  Resolver.sonatypeRepo("snapshots")
)

libraryDependencies ++= Seq(
  "org.macroid" %% "macroid" % "1.0.0-20130925",
  "com.android.support" % "support-v13" % "13.0.0"
)

proguardOptions in Android += "-ignorewarnings"