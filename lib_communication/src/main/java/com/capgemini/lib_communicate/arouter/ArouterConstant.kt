package com.capgemini.lib_communicate.arouter

import com.capgemini.lib_communicate.arouter.IProviderARouterPath.Companion.PROVIDER_PROFILE_MODULE

/*****************************************ARouter login interceptor********************************/

val interceptorPaths = arrayListOf<String>().apply {
    add(PROVIDER_PROFILE_MODULE)
}
val ambiguousInterceptorPaths = arrayListOf<String>().apply {
    add("nothing currently")
}


/*****************************************ARouter parameters **************************************/

interface RouterExtra {
    companion object {
        const val SELECTED_TAB_INDEX = "selected_tab_index"
        const val SELECTED_TAB_INDEX_ONE = 1
        const val SELECTED_TAB_INDEX_TWO = 2
        const val SELECTED_TAB_INDEX_THREE = 3
        const val IGNORE_LOGIN = "ignore_login"
        const val INTERCEPTED_PATH = "intercepted_path"
        const val REPOS_DERAILS = "reposDetails"
    }
}

/*****************************************ARouter IProvider ***************************************/

interface IProviderARouterPath {
    companion object {
        /**
         * profile 模块对外提供数据接口
         */
        const val PROVIDER_PROFILE_MODULE = "/provider/profile"
    }
}

/*****************************************ARouter Activity path ***********************************/


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



/**
 * Profile module ARouter的路径
 */
interface SampleModuleARouterPath {
    companion object {
        const val SAMPLE_MAIN = "/sample/main"
        const val SAMPLE_FRAGMENT = "/sample/fragment"
        const val SAMPLE_LOGIN = "/sample/login"
        const val SAMPLE_LIST = "/sample/list"
        const val SAMPLE_COMMUNITY = "/sample/community"
        const val SAMPLE_SCROLL = "/sample/scroll"
        const val SAMPLE_REPOS_DETAILS = "/sample/reposDetails"
        const val SAMPLE_REPOS = "/sample/repos"
        const val SAMPLE_GITHUB_LOGIN = "/sample/gitHubLogin"
    }
}
