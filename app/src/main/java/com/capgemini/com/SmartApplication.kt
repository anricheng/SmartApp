package com.capgemini.com

import com.alibaba.android.arouter.launcher.ARouter
import com.capgemini.lib_common.base.BaseApplication
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SmartApplication: BaseApplication() {
    override fun onCreate() {
        super.onCreate()
        ARouter.openDebug()
        ARouter.init(this)
    }
}