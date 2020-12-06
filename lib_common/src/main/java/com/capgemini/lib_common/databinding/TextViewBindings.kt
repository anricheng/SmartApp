package com.capgemini.lib_common.databinding

import android.graphics.Typeface
import androidx.databinding.BindingConversion
import com.capgemini.lib_common.base.GlobalApplicationContext
import com.capgemini.lib_common.utils.FontUtils

@BindingConversion
fun convertStringToTypeFace(style: String): Typeface = FontUtils.getTypeface(GlobalApplicationContext, style)
