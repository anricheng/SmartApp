package com.capgemini.com.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.capgemini.lib_base.arouter.NavigationHelper
import com.capgemini.lib_base.arouter.ProfileModuleARouterPath.Companion.PROFILE_LOGIN
import com.capgemini.repository.MainRepository

class ModuleEntranceViewModel @ViewModelInject constructor(private val mainRepository: MainRepository) : ViewModel(){

    fun navigateToProfileModule() {
        NavigationHelper.navigation(PROFILE_LOGIN)
    }

    fun navigateToSampleModule() {
        // TODO: 2020/12/3 待实现
    }
}

