package com.capgemini.config

import android.content.Context

/**
 * Provides essential information for Http Request architecture,and should be initialized in advance
 */
object HttpConfig {
    var mContext: Context? = null
    var mAccessToken: String = ""
    var mLoginPath: String = ""
    var login: (() -> Unit)? = null
}