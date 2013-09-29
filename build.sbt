import android.Keys._

android.Plugin.androidBuild

platformTarget in Android := "android-17"

name := "macroid-starter"

scalaVersion := "2.10.2"

resolvers ++= Seq(
  "jrebel" at "http://jcenter.bintray.com"
)

libraryDependencies ++= Seq(
  "org.macroid" %% "macroid" % "1.0.0-20130929",
  "com.android.support" % "support-v13" % "13.0.0"
)

proguardScala in Android := true

proguardCache in Android += ProguardCache("org.macroid") % "org.macroid" %% "macroid"

proguardOptions in Android += "-ignorewarnings"
