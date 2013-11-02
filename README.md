macroid-starter
===============

A sample project to start playing with [Macroid](https://github.com/stanch/macroid).

* Install *sbt* from http://www.scala-sbt.org/release/docs/Getting-Started/Setup.html
* Install *Android SDK* and set `ANDROID_SDK_HOME`; you should install the latest API (18) and the *Repositories* package.
* `git clone https://github.com/stanch/macroid-starter && cd macroid-starter`
* `sbt android:run`

If you want to use *INTELLIJ IDEA* to work with the project:

* Install IDEA ( *Android Studio* works as well).
* Install Scala plugin from http://plugins.jetbrains.com/plugin/1347 or from IDEA settings.
* Install sbt plugin from http://plugins.jetbrains.com/plugin/5007 or from IDEA settings.
* `sbt gen-idea` will generate project files for you.
  
  Depending on IDEA version, you may need to go to project settings → modules → macroid-starter and select correct Android SDK (see https://github.com/mpeltonen/sbt-idea/issues/263).
* If you see lots of red code, click “disable type-aware highlighting” ([T] at the bottom), it’s really messy.
* Inside IDEA run the sbt console (or open terminal and type `sbt`) and type `android:run` there. You can also use `compile` and other normal sbt stuff.
* Each time you update the dependencies, run `sbt gen-idea` again.

Important:

* Learn Scala:
  * for the patient: http://www.artima.com/pins1ed/
  * for the impatient: [Quick intro](http://docs.scala-lang.org/tutorials/scala-for-java-programmers.html), [More coverage](http://twitter.github.io/scala_school/)
* Read sbt docs! http://www.scala-sbt.org/
* Read android plugin docs! https://github.com/pfn/android-sdk-plugin
* Read macroid docs! http://macroid.herokuapp.com/
