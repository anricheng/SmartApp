package com.capgemini.com.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.capgemini.lib_communicate.arouter.NavigationHelper
import com.capgemini.lib_communicate.arouter.SampleModuleARouterPath.Companion.SAMPLE_MAIN
import com.capgemini.repository.SampleRepository1

class ModuleEntranceViewModel @ViewModelInject constructor(private val sampleRepository1: SampleRepository1) : ViewModel(){

    fun navigateToProfileModule() {

    }

    fun navigateToSampleModule() {
        NavigationHelper.navigation(SAMPLE_MAIN)
    }
}

