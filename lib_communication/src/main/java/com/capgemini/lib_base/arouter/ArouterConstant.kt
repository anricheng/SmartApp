package com.capgemini.lib_base.arouter

import com.capgemini.lib_base.arouter.IProviderARouterPath.Companion.PROVIDER_PROFILE_MODULE

/**
 * 登录拦截器所需
 */
val interceptorPaths = arrayListOf<String>().apply {
    add(PROVIDER_PROFILE_MODULE)
}
val ambiguousInterceptorPaths = arrayListOf<String>().apply {
    add("nothing currently")
}

/**
 * ARouter 跳转携带的参数key以及value的说明
 */
interface RouterExtra {
    companion object {
        const val SELECTED_TAB_INDEX = "selected_tab_index"
        const val SELECTED_TAB_INDEX_ONE = 1
        const val SELECTED_TAB_INDEX_TWO = 2
        const val SELECTED_TAB_INDEX_THREE = 3
        const val IGNORE_LOGIN = "ignore_login"
        const val INTERCEPTED_PATH = "intercepted_path"
    }
}

/**
 * ARouter IProvider 相关的路径
 */
interface IProviderARouterPath {
    companion object {
        /**
         * profile 模块对外提供数据接口
         */
        const val PROVIDER_PROFILE_MODULE = "/provider/profile"
    }
}

/**
 * Profile module ARouter的路径
 */
interface ProfileModuleARouterPath {
    companion object {
        /**
         * @module profile module
         * @param key:[RouterExtra.SELECTED_TAB_INDEX] value:[RouterExtra.SELECTED_TAB_INDEX_ONE],[RouterExtra.SELECTED_TAB_INDEX_TWO],[RouterExtra.SELECTED_TAB_INDEX_THREE]
         */
        const val PROFILE_LOGIN = "/profile/login"
        const val PROFILE_SAMPLE = "/profile/sample"
    }
}
