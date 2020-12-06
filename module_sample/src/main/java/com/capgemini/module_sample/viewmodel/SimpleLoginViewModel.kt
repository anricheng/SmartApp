package com.capgemini.module_sample.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capgemini.lib_common.extendtions.*
import com.capgemini.lib_communicate.arouter.NavigationHelper
import com.capgemini.lib_communicate.arouter.SampleModuleARouterPath.Companion.SAMPLE_FRAGMENT
import com.capgemini.lib_communicate.sp.DataClassSample
import com.capgemini.lib_communicate.sp.ProfileInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SimpleLoginViewModel : ViewModel() {
    private var userNameLengthCount = 0
    private var passwordLengthCount = 0

    val userName = MutableLiveData(ProfileInfo.userName)
    val userNameError = MediatorLiveData<String>().apply {
        addSource(userName) {
            value = it.isValidateUseName()
                .isTrue { "" }
                .otherwise {
                    when (it.length) {
                        0 -> ""
                        in 0..userNameLengthCount -> "this is not a valid value"
                        else -> ""
                    }
                }
            userNameLengthCount = it.length
        }
    }

    val password = MutableLiveData(ProfileInfo.password)
    val passwordError = MediatorLiveData<String>().apply {
        addSource(password) {
            value = it.isValidatePassword()
                .isTrue { "" }
                .otherwise {
                    when (it.length) {
                        0 -> ""
                        in 0..passwordLengthCount -> "this is not a valid value"
                        else -> ""
                    }
                }
            passwordLengthCount = it.length
        }
    }

    val validate = MediatorLiveData<Boolean>().apply {
        addSource(userName) { value = isValidate() }
        addSource(password) { value = isValidate() }
    }

    private fun isValidate(): Boolean {
        return password.value.isValidatePassword() && userName.value.isValidateUseName()
    }

    fun goToFragmentActivity() {
        NavigationHelper.navigation(SAMPLE_FRAGMENT)
    }

    fun login() {
        viewModelScope.launch(Dispatchers.Main) {
            showLoading()
            delay(1000)
            hideLoading()
            ProfileInfo.dataClassSample = DataClassSample("海峰好帅")
            toastLg("模拟登陆成功${ProfileInfo.dataClassSample.data1}")
            ProfileInfo.userName = userName.value.toString()
            ProfileInfo.password = password.value.toString()
        }
    }

}