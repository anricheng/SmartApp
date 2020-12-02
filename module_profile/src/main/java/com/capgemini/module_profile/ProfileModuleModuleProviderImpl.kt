package com.capgemini.module_profile

import android.content.Context
import com.capgemini.lib_base.arouter.ProfileModuleProvider

class ProfileModuleModuleProviderImpl:ProfileModuleProvider {
    override fun getUserId(): String? {
       return "zhou"
    }

    override fun getUserToken(): String? {
        return "hai"

    }

    override fun init(context: Context?) {

    }

}