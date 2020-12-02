package com.capgemini.lib_common.databinding

import android.graphics.Typeface
import androidx.databinding.BindingConversion
import com.capgemini.lib_common.base.ApplicationGlobalContext
import com.capgemini.lib_common.utils.FontUtils

@BindingConversion
fun convertStringToTypeFace(style: String): Typeface = FontUtils.getTypeface(ApplicationGlobalContext, style)
