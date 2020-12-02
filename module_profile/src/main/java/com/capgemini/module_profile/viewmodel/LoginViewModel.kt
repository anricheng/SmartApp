package com.capgemini.module_profile.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.ActivityUtils
import com.capgemini.lib_base.arouter.NavigationHelper
import com.capgemini.lib_base.arouter.ProfileModuleARouterPath.Companion.PROFILE_SAMPLE
import com.capgemini.lib_base.sp.ProfileInfo
import com.capgemini.lib_base.sp.Zhou
import com.capgemini.lib_common.extendtions.*
import com.capgemini.lib_common.widget.Loading
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private var userNameLengthCount = 0
    private var passwordLengthCount = 0

    val userName = MutableLiveData<String>(ProfileInfo.userName)
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

    val password = MutableLiveData<String>(ProfileInfo.password)
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
        NavigationHelper.navigation(PROFILE_SAMPLE)
    }

    fun login() {
        viewModelScope.launch(Dispatchers.Main) {
            Loading.show(ActivityUtils.getTopActivity())
            delay(1000)
            Loading.dismiss(ActivityUtils.getTopActivity())
            ProfileInfo.zhou = Zhou("海峰好帅")
            ActivityUtils.getTopActivity().toastLg("模拟登陆成功${ProfileInfo.zhou.zhou}")
            ProfileInfo.userName = userName.value.toString()
            ProfileInfo.password = password.value.toString()
        }
    }

}