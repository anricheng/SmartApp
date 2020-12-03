package com.capgemini.lib_base.arouter

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.callback.NavigationCallback
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.Utils
import com.capgemini.lib_common.extendtions.isTrue
import com.capgemini.lib_common.extendtions.notNull
import com.capgemini.lib_common.extendtions.otherwise
import java.io.Serializable

object NavigationHelper {
    /**  多参数的navigation
     * @param path   ARouter路径
     * @param bundle 数据
     */
    fun navigation(path: String, bundle: Bundle? = null) {
        bundle.notNull {
            ARouter.getInstance().build(path).navigation(
                Utils.getApp(),
                LoginNavigationCallback()
            )
        }
            .otherwise {
                ARouter.getInstance().build(path).with(bundle).navigation(
                    Utils.getApp(),
                    LoginNavigationCallback()
                )
            }
    }

    /**  单参数的navigation
     * @param path   ARouter路径
     * @param key key
     * @param value value
     */
    fun navigation(path: String, key: String, value: Any) {
        navigationForResult(null, path, -1, key, value)
    }

    /**  单参数的navigationForResult
     * @param path   ARouter路径
     * @param key key
     * @param value value
     */
    fun navigationForResult(
        activity: Activity?,
        path: String,
        requestCode: Int,
        key: String,
        value: Any
    ) {
        var block: Postcard.() -> Postcard = {
            when (value) {
                is String -> withString(key, value)
                is Int -> withInt(key, value)
                is Boolean -> withBoolean(key, value)
                is Float -> withFloat(key, value)
                is Long -> withLong(key, value)
                is Parcelable -> withParcelable(key, value)
                is Serializable -> withSerializable(key, value)
                else -> throw IllegalArgumentException("Unsupported type.")
            }
        }

        (activity != null && requestCode > 0).isTrue {
            with(ARouter.getInstance().build(path), block).navigation(
                activity,
                requestCode,
                LoginNavigationCallback()
            )

        }.otherwise {
            with(ARouter.getInstance().build(path), block).navigation(
                Utils.getApp(),
                LoginNavigationCallback()
            )
        }
    }

    /**  多参数的navigationForResult
     * @param path   ARouter路径
     * @param bundle 数据
     */
    fun navigationForResult(
        activity: Activity,
        path: String,
        requestCode: Int,
        bundle: Bundle? = null
    ) {
        bundle.notNull {
            ARouter.getInstance().build(path).navigation(
                activity,
                requestCode,
                LoginNavigationCallback()
            )
        }
            .otherwise {
                ARouter.getInstance().build(path).with(bundle).navigation(
                    activity,
                    requestCode,
                    LoginNavigationCallback()
                )
            }
    }

    /**
     * @param path ARouter路径
     * @param flag Intent.FLAG
     */
    fun navigationWithFlag(context: Context, path: String, flag: Int) {
        ARouter.getInstance().build(path).withFlags(flag).navigation(
            context,
            LoginNavigationCallback()
        )
    }

    /**
     * @param path ARouter路径
     * @param flag Intent.FLAG
     */
    fun navigationWithExtrasAndFlag(context: Context, path: String, extras: Bundle, flag: Int) {
        ARouter.getInstance().build(path).with(extras).withFlags(flag).navigation(
            context,
            LoginNavigationCallback()
        )
    }

    /**
     * @param path     ARouter路径
     * @param bundle   数据
     * @param callback 自定callback处理
     */
    fun navigation(path: String?, bundle: Bundle?, callback: NavigationCallback?) {
        if (bundle == null) {
            ARouter.getInstance().build(path).navigation(Utils.getApp(), callback)
        } else {
            ARouter.getInstance().build(path).with(bundle).navigation(Utils.getApp(), callback)
        }
    }
}