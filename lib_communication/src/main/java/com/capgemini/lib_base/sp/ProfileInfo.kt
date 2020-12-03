package com.capgemini.lib_base.sp

import com.capgemini.lib_common.delegate.PreferenceDelegate

object ProfileInfo {
    private const val prefName: String = "ProfileInfo"
    var userName: String by PreferenceDelegate(prefName)
    var password: String by PreferenceDelegate(prefName)
    var accessToken: String by PreferenceDelegate(prefName)
    var refreshToken: String by PreferenceDelegate(prefName)
    var isFirstOpen: Boolean by PreferenceDelegate(prefName)
    var dataClassSample: DataClassSample by PreferenceDelegate(prefName)
}


data class DataClassSample(val data1: String = "zhou", val data2: String = "hai")