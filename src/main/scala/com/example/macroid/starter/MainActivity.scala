package com.example.macroid.starter

import android.os.Bundle
import android.widget.{LinearLayout, TextView, Button}
import android.view.ViewGroup.LayoutParams._
import android.view.{Gravity, View}
import android.app.Activity

// import macroid stuff
import macroid._
import macroid.FullDsl._
import macroid.contrib._

import scala.concurrent.ExecutionContext.Implicits.global

// define our helpers in a mixable trait
trait Styles {
  // sets text, large font size and a long click handler
  def caption(cap: String)(implicit ctx: AppContext): Tweak[TextView] =
    text(cap) + TextTweaks.large + On.longClick {
      (toast("I’m a caption") <~ gravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL) <~ fry) ~
      Ui(true)
    }
}

// mix in Contexts for Activity
class MainActivity extends Activity with Styles with Contexts[Activity] {
  // prepare a variable to hold our text view
  var cap = slot[TextView]

  override def onCreate(savedInstanceState: Bundle) = {
    super.onCreate(savedInstanceState)
    // this will be a linear layout
    val view = l[LinearLayout](
      // a text view
      w[TextView] <~
        // use our helper
        caption("Howdy?") <~
        // assign to cap
        wire(cap),

      // a button
      w[Button] <~
        // set text
        text("Click me!") <~
        // set layout params (LinearLayout.LayoutParams will be used)
        layoutParams[LinearLayout](MATCH_PARENT, WRAP_CONTENT) <~
        // set click handler
        On.click {
          // with <~~ we can apply snails like `delay`
          // tweaks coming after them will wait till they finish
          cap <~ text("Button clicked!") <~~ delay(1000) <~ text("Howdy")
        }
    ) <~
      // match layout orientation to screen orientation
      (portrait ? vertical | horizontal) <~ Transformer {
        // here we set a padding of 4 dp for all inner views
        case x: View ⇒ x <~ padding(all = 4 dp)
      }

    setContentView(getUi(view))
  }
}
