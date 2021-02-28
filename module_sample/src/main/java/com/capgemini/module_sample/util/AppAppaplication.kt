package com.capgemini.module_sample.util

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.capgemini.module_sample.BuildConfig

class AppAppaplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            ARouter.openLog();//打开日志
            ARouter.openDebug();//打开调式模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this);
    }
}