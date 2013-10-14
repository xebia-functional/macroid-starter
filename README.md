macroid-starter
===============

A sample project to start playing with [Macroid](https://github.com/stanch/macroid).

* Install *sbt* from http://www.scala-sbt.org/release/docs/Getting-Started/Setup.html
* Install *Android SDK* and set `ANDROID_SDK_HOME`
* `git clone https://github.com/stanch/macroid-starter && cd macroid-starter`
* `sbt android:run`

If you want to use *INTELLIJ IDEA* to work with the project:

* Install IDEA ( *Android Studio* works for me as well)
* Install Scala plugin from http://plugins.jetbrains.com/plugin/1347
* Install sbt plugin from http://plugins.jetbrains.com/plugin/5007 (For Android Studio 0.1, version 1.5.0 works well; for AS 0.2, use terminal)
* `sbt gen-idea` will generate project files for you. When you open the project, IDEA will suggest to add Android facet — go for it.
* Inside IDEA run the sbt console (or terminal) and type `android:run` there. You can also use `compile` and other normal sbt stuff.
* Each time you update the dependencies, run `sbt gen-idea` again.

Important:

* Learn Scala at Scala School! http://twitter.github.io/scala_school/
* Read sbt docs! http://www.scala-sbt.org/
* Read android plugin docs! https://github.com/pfn/android-sdk-plugin
* Read macroid docs! http://macroid.herokuapp.com/
