package com.capgemini.com.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.capgemini.lib_base.arouter.NavigationHelper
import com.capgemini.lib_base.arouter.ProfileModuleARouterPath.Companion.PROFILE_LOGIN
import com.capgemini.repository.SampleRepository1

class ModuleEntranceViewModel @ViewModelInject constructor(private val sampleRepository1: SampleRepository1) : ViewModel(){

    fun navigateToProfileModule() {
        NavigationHelper.navigation(PROFILE_LOGIN)
    }

    fun navigateToSampleModule() {
        // TODO: 2020/12/3 待实现
    }
}

