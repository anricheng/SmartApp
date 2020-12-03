package com.capgemini.com

import android.content.Context
import androidx.multidex.MultiDex
import com.alibaba.android.arouter.launcher.ARouter
import com.capgemini.lib_common.base.BaseApplication
import com.capgemini.lib_common.extendtions.isTrue
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SmartApplication: BaseApplication() {
    override fun onCreate() {
        super.onCreate()
        BuildConfig.DEBUG.isTrue {  ARouter.openDebug() }
        ARouter.init(this)
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}