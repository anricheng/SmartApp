package com.capgemini.lib_common.extendtions

import android.content.Context
import android.widget.Toast
import androidx.annotation.FontRes
import androidx.core.content.res.ResourcesCompat

fun Context.screenWith(): Int = this.resources.displayMetrics.widthPixels

fun Context.screenHeight(): Int = this.resources.displayMetrics.heightPixels

fun Context.getFont(@FontRes resId: Int) = ResourcesCompat.getFont(this, resId)

fun Context.toastSt(text: String) = Toast.makeText(this.applicationContext, text, Toast.LENGTH_SHORT).show()

fun Context.toastLg(text: String) = Toast.makeText(this.applicationContext, text, Toast.LENGTH_LONG).show()

