package org.macroid.starter

import android.support.v4.app.FragmentActivity
import android.widget.{Toast, TextView, Button}
import android.os.Bundle
import org.macroid.{LayoutDsl, FullDslActivity}
import org.macroid.contrib.Layouts.VerticalLinearLayout
import org.macroid.contrib.ExtraTweaks
import android.content.Context
import android.view.ViewGroup.LayoutParams._
import scala.concurrent.future
import scala.concurrent.ExecutionContext.Implicits.global
import org.macroid.util.Functors._

// define our helpers in a mixable trait
trait Styles extends LayoutDsl with ExtraTweaks {
  // sets text, large font size and a long click handler
  def caption(cap: String)(implicit ctx: Context): Tweak[TextView] = text(cap) + TextSize.large + On.longClick {
    Toast.makeText(ctx, "I’m a caption", Toast.LENGTH_SHORT).show()
    true
  }
}

class MainActivity extends FragmentActivity with FullDslActivity with Styles {
  // prepare a variable to hold our text view
  var cap: TextView = _

  override def onCreate(savedInstanceState: Bundle) {
    super.onCreate(savedInstanceState)
    // this will be a vertical LinearLayout
    setContentView(l[VerticalLinearLayout](
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
          // with ~> we can not only apply tweaks, but also apply tweaks, that will happen in the future
          cap ~> text("Button clicked!") ~> future { Thread.sleep(1000); text("Howdy") }
        }
    ))
  }
}
