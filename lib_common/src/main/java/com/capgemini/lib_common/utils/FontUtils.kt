package com.capgemini.lib_common.utils

import android.content.Context
import android.graphics.Typeface
import com.capgemini.lib_common.extendtions.isTrue
import com.capgemini.lib_common.extendtions.otherwise
import java.util.*

object FontUtils {
    private val mTypefaceMap: MutableMap<String, Typeface> = HashMap()
    fun getTypeface(context: Context, style: String): Typeface =
        mTypefaceMap.containsKey(style).isTrue {
            mTypefaceMap[style]!!
        }.otherwise {
            loadTypeface(context, style).also { mTypefaceMap[style] = it }
        }

    private fun loadTypeface(context: Context, style: String): Typeface = try {
        Typeface.createFromAsset(context.assets, "fonts/$style")
    } catch (e: Exception) {
        e.printStackTrace()
        Typeface.DEFAULT
    }
}