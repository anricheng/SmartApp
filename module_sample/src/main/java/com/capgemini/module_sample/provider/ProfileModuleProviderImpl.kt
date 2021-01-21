package com.capgemini.module_sample.provider

import android.content.Context
import com.capgemini.lib_communicate.arouter.ProfileModuleProvider

class ProfileModuleProviderImpl: ProfileModuleProvider {
    override fun getUserId(): String?="mockUserId"

    override fun getUserToken(): String? = "mockUserToken"

    override fun init(context: Context?) {
       print("It's a just sample list")
    }
}