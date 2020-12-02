package com.capgemini.lib_common.extendtions

import android.content.res.Resources

val Float.toPx: Float get() = (this * Resources.getSystem().displayMetrics.density)

val Int.toPx: Int get() = ((this * Resources.getSystem().displayMetrics.density).toInt())