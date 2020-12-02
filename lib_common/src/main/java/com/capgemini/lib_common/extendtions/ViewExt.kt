package com.capgemini.lib_common.extendtions

import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import com.capgemini.lib_common.databinding.THROTTLE_FIRST_CLICK_TIME_SECONDS
import com.jakewharton.rxbinding4.view.clicks
import java.util.concurrent.TimeUnit


fun View.throttleFirstClick(
    milliSeconds: Long = THROTTLE_FIRST_CLICK_TIME_SECONDS,
    listener: View.OnClickListener
) {
    this.clicks().throttleFirst(milliSeconds, TimeUnit.MILLISECONDS).subscribe {
        listener.onClick(this)
    }
}

@set:BindingAdapter("isVisible")
inline var View.isVisible: Boolean
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if (value) View.VISIBLE else View.GONE
    }

fun View.setBackgroundColorE(@ColorRes id: Int) {
    this.setBackgroundColor(this.context.resources.getColor(id))
}

fun View.setBackgroundE(@DrawableRes id: Int) {
    this.background = this.context.resources.getDrawable(id)
}

fun View.MarginTop(marginValue: Int) {
    (this.layoutParams as ViewGroup.MarginLayoutParams).topMargin = marginValue.toPx
}

fun View.MarginBottom(marginValue: Int) {
    (this.layoutParams as ViewGroup.MarginLayoutParams).bottomMargin = marginValue.toPx
}

fun View.MarginLeft(marginValue: Int) {
    (this.layoutParams as ViewGroup.MarginLayoutParams).leftMargin = marginValue.toPx
}

fun View.MarginRight(marginValue: Int) {
    (this.layoutParams as ViewGroup.MarginLayoutParams).rightMargin = marginValue.toPx
}

fun View.screenWith(): Int = this.resources.displayMetrics.widthPixels

fun View.screenHeight(): Int = this.resources.displayMetrics.heightPixels

