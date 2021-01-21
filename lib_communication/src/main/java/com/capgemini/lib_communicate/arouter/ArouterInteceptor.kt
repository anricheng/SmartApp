package com.capgemini.lib_communicate.arouter

import android.content.Context
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.annotation.Interceptor
import com.alibaba.android.arouter.facade.callback.InterceptorCallback
import com.alibaba.android.arouter.facade.template.IInterceptor
import com.alibaba.android.arouter.launcher.ARouter
import com.capgemini.lib_communicate.arouter.RouterExtra.Companion.IGNORE_LOGIN
import com.capgemini.lib_common.extendtions.isTrue
import com.capgemini.lib_common.extendtions.notNull
import com.capgemini.lib_common.extendtions.notNullOrEmpty
import com.capgemini.lib_common.extendtions.otherwise

//@Interceptor(name = "login", priority = 6)
//class LoginInterceptor : IInterceptor {
//    private lateinit var profileModuleProvider: ProfileModuleProvider
//    override fun process(postcard: Postcard, callback: InterceptorCallback) {
//        profileModuleProvider.getUserId()
//            .notNullOrEmpty {
//                callback.onContinue(postcard)
//            }
//            .otherwise {
//                when (postcard.path) {
//                    in interceptorPaths -> callback.onInterrupt(null)
//                    in ambiguousInterceptorPaths -> {
//                        postcard.extras
//                            .notNull {
//                                it.getBoolean(IGNORE_LOGIN, true)
//                                    .isTrue {
//                                        callback.onContinue(postcard)
//                                    }
//                                    .otherwise {
//                                        callback.onInterrupt(null)
//                                    }
//                            }
//                            .otherwise {
//                                callback.onContinue(postcard)
//                            }
//                    }
//                    else -> callback.onContinue(postcard)
//                }
//            }
//    }
//
//    override fun init(context: Context) {
//        profileModuleProvider = ARouter.getInstance().navigation(ProfileModuleProvider::class.java)
//    }
//}