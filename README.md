macroid-starter
===============

A sample project to start playing with [Macroid](https://github.com/stanch/macroid).

* Install *sbt* from http://www.scala-sbt.org/release/docs/Getting-Started/Setup.html
* Install *Android SDK* and set `ANDROID_SDK_HOME`
* `git clone https://github.com/stanch/macroid-starter && cd macroid-starter`
* `sbt android:run`

If you want to use *INTELLIJ IDEA* to work with the project:

* Install IDEA (*Android Studio* works for me, too!)
* Install Scala plugin from http://plugins.jetbrains.com/plugin/1347
  (Android Studio users: download the file from the site, go to plugins and install manually. I use version 0.12.12)
* Install sbt plugin from http://plugins.jetbrains.com/plugin/5007 (Android Studio works at least with version 1.5.0)
* `sbt gen-idea` will generate project files for you. When you open the project, IDEA will suggest to add Android facet — go for it.
* Inside IDEA run the sbt console and type `android:run` there. You can also use `compile` and other normal sbt stuff.
* Each time you update the dependencies, run `sbt gen-idea` again.

Important:

* Learn Scala at Scala School! http://twitter.github.io/scala_school/
* Read sbt docs! http://www.scala-sbt.org/
* Read android plugin docs! https://github.com/pfn/android-sdk-plugin
* Read macroid docs! https://github.com/stanch/macroid#macroid--a-scala-gui-dsl-for-android
