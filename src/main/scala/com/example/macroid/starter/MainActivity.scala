package com.example.macroid.starter

import android.support.v4.app.FragmentActivity
import android.widget.{LinearLayout, TextView, Button}
import android.os.Bundle
import org.macroid.{Toasts, LayoutDsl, FullDslActivity}
import org.macroid.contrib.ExtraTweaks
import android.content.Context
import android.view.ViewGroup.LayoutParams._
import scala.concurrent.ExecutionContext.Implicits.global
import android.view.{Gravity, View}

// define our helpers in a mixable trait
trait Styles extends LayoutDsl with ExtraTweaks with Toasts {
  // sets text, large font size and a long click handler
  def caption(cap: String)(implicit ctx: Context): Tweak[TextView] =
    text(cap) + TextSize.large + On.longClick {
      // create and show a toast
      toast("I’m a caption") ~> gravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL) ~> fry
      true
    }
}

class MainActivity extends FragmentActivity with FullDslActivity with Styles {
  // prepare a variable to hold our text view
  var cap = slot[TextView]

  override def onCreate(savedInstanceState: Bundle) {
    super.onCreate(savedInstanceState)
    // this will be a vertical LinearLayout
    val view = l[LinearLayout](
      // a text view
      w[TextView] ~>
        // use our helper
        caption("Howdy?") ~>
        // assign to cap
        wire(cap),

      // a button
      w[Button] ~>
        // set text
        text("Click me!") ~>
        // set layout params (LinearLayout.LayoutParams will be used)
        layoutParams(MATCH_PARENT, WRAP_CONTENT) ~>
        // set click handler
        On.click {
          // with ~@> we can apply snails like `delay`
          // tweaks coming after them will wait till they finish
          cap ~> text("Button clicked!") ~@> delay(1000) ~> text("Howdy")
        }
    ) ~>
      // match layout orientation to screen orientation
      (portrait ? vertical | horizontal) ~~> {
        // ~~> applies the supplied function to all children, grand-children, ...
        // here we set a padding of 4 dp for all inner views
        case x: View ⇒ x ~> padding(all = 4 dp)
      }
    setContentView(view)
  }
}
