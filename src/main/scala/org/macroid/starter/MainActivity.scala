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

trait Styles extends LayoutDsl with ExtraTweaks {
  def caption(cap: String)(implicit ctx: Context): Tweak[TextView] = text(cap) + TextSize.large + On.longClick {
    Toast.makeText(ctx, "I’m a caption", Toast.LENGTH_SHORT).show()
    true
  }
}

class MainActivity extends FragmentActivity with FullDslActivity with Styles {
  var cap: TextView = _

  override def onCreate(savedInstanceState: Bundle) {
    super.onCreate(savedInstanceState)
    setContentView(l[VerticalLinearLayout](
      w[TextView] ~>
        caption("Howdy?") ~>
        wire(cap),

      w[Button] ~>
        text("Click me!") ~>
        layoutParams(MATCH_PARENT, WRAP_CONTENT) ~>
        On.click {
          cap ~> text("Button clicked!") ~> future { Thread.sleep(1000); text("Howdy") }
        }
    ))
  }
}
