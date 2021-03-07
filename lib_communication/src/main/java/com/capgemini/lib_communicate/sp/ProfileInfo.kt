package com.capgemini.lib_communicate.sp

import com.capgemini.lib_common.delegate.SharePreferenceDelegate

object ProfileInfo {
    private const val prefName: String = "ProfileInfo"
    var userName: String by SharePreferenceDelegate(prefName)
    var password: String by SharePreferenceDelegate(prefName)
    var code: String by SharePreferenceDelegate(prefName)
    var accessToken: String by SharePreferenceDelegate(prefName)
    var refreshToken: String by SharePreferenceDelegate(prefName)
    var isFirstOpen: Boolean by SharePreferenceDelegate(prefName)
    var dataClassSample: DataClassSample by SharePreferenceDelegate(prefName)
}


data class DataClassSample(val data1: String = "zhou", val data2: String = "hai")