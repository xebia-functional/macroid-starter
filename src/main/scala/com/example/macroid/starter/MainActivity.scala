package com.example.macroid.starter

import scala.language.postfixOps

import android.os.Bundle
import android.widget.{ListView, LinearLayout, TextView, Button}
import android.view.ViewGroup.LayoutParams._
import android.view.{Gravity, View}
import android.app.Activity
import android.graphics.Color

// import macroid stuff
import macroid._
import macroid.FullDsl._
import macroid.contrib._
import macroid.viewable._

import scala.concurrent.ExecutionContext.Implicits.global

// a simple case class to demonstrate listing things
case class ColorString(text: String, color: Int)

// define our helpers in a mixable trait
trait Styles {
  // sets text, large font size and a long click handler
  def caption(cap: String)(implicit appCtx: AppContext): Tweak[TextView] =
    text(cap) + TextTweaks.large + On.longClick {
      (toast("I’m a caption") <~ gravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL) <~ fry) ~
      Ui(true)
    }

  // allows to display colored strings in a ListView
  def colorStringListable(implicit ctx: ActivityContext, appCtx: AppContext): Listable[ColorString, TextView] =
    Listable[ColorString].tw(
      w[TextView] <~ TextTweaks.typeface("sans-serif-condensed") <~ TextTweaks.medium
    ) { colorString ⇒
      text(colorString.text) + TextTweaks.color(colorString.color)
    }
}

// mix in Contexts for Activity
class MainActivity extends Activity with Styles with Contexts[Activity] {
  // prepare a variable to hold our text view
  var cap = slot[TextView]

  // some colored strings
  val colorStrings = List(
    ColorString("Coquelicot", Color.parseColor("#EC4908")),
    ColorString("Smaragdine", Color.parseColor("#009874")),
    ColorString("Glaucous",   Color.parseColor("#6082B6"))
  )

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
        // specify a background image
        BgTweaks.res(R.drawable.btn_green_matte) <~
        // set click handler
        On.click {
          // with <~~ we can apply snails like `delay`
          // tweaks coming after them will wait till they finish
          cap <~ text("Button clicked!") <~~ delay(1000) <~ text("Howdy")
        },

      // a list view
      w[ListView] <~
        // use a listable to display the colored strings
        colorStringListable.listAdapterTweak(colorStrings)
    ) <~
      // match layout orientation to screen orientation
      (portrait ? vertical | horizontal) <~ Transformer {
        // here we set a padding of 4 dp for all inner views
        case x: View ⇒ x <~ padding(all = 4 dp)
      }

    setContentView(getUi(view))
  }
}
