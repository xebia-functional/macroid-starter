import android.Keys._

android.Plugin.androidBuild

platformTarget in Android := "android-17"

name := "macroid-starter"

scalaVersion := "2.10.3"

resolvers ++= Seq(
  "jrebel" at "http://jcenter.bintray.com"
)

libraryDependencies ++= Seq(
  "org.macroid" %% "macroid" % "1.0.0",
  "io.dylemma" %% "scala-frp" % "1.0",
  "com.android.support" % "support-v13" % "13.0.0"
)

proguardScala in Android := true

proguardCache in Android += ProguardCache("scalaz") % "org.scalaz"

proguardOptions in Android ++= Seq(
  "-ignorewarnings",
  "-keep class scala.Dynamic"
)
