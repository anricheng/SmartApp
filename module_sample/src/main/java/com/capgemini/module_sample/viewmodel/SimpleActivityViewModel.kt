package com.capgemini.module_sample.viewmodel

import android.Manifest
import android.text.Editable
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capgemini.entity.TaskEntity
import com.capgemini.lib_common.extendtions.isTrue
import com.capgemini.lib_common.extendtions.otherwise
import com.capgemini.lib_common.extendtions.requestPermission
import com.capgemini.lib_common.extendtions.toastLg
import com.capgemini.lib_common.utils.SimpleTextWatcher
import com.capgemini.lib_communicate.arouter.NavigationHelper
import com.capgemini.lib_communicate.arouter.SampleModuleARouterPath
import com.capgemini.lib_communicate.arouter.SampleModuleARouterPath.Companion.SAMPLE_COMMUNITY
import com.capgemini.lib_communicate.arouter.SampleModuleARouterPath.Companion.SAMPLE_LIST
import com.capgemini.lib_communicate.arouter.SampleModuleARouterPath.Companion.SAMPLE_LOGIN
import com.capgemini.repository.SampleRepository1
import kotlinx.coroutines.launch

class SimpleMainActivityViewModel @ViewModelInject constructor(private val sampleRepository1: SampleRepository1) : ViewModel(){

    private val _task = MutableLiveData<List<TaskEntity>>()
    val task: LiveData<List<TaskEntity>> = _task

    //two-way binding
    val taskId = MutableLiveData("")

    val textWatcher = object : SimpleTextWatcher() {
        override fun afterTextChanged(s: Editable) {
            viewModelScope.launch {
            }
        }
    }

    fun getPhonePermission(){
        requestPermission(Manifest.permission.CALL_PHONE,Manifest.permission.READ_PHONE_STATE){
            toastLg("用户${it.isTrue { "同意了" }.otherwise { "拒绝了" }}")
        }
    }

    fun navigateToLogin(){
        NavigationHelper.navigation(SAMPLE_LOGIN)
    }

    fun goToFragmentActivity() {
        NavigationHelper.navigation(SampleModuleARouterPath.SAMPLE_FRAGMENT)
    }

    fun navigateToListActivity(){
        NavigationHelper.navigation(SAMPLE_LIST)
    }


    fun navigateToCommunityActivity(){
        NavigationHelper.navigation(SAMPLE_COMMUNITY)
    }

}

