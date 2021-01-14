package com.capgemini.lib_common.base

import android.app.Application
import android.content.ContextWrapper
import com.luliang.shapeutils.DevShapeUtils

private lateinit var INSTANCE:Application

open class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        DevShapeUtils.init(this);
    }
}

object GlobalApplicationContext:ContextWrapper(INSTANCE)