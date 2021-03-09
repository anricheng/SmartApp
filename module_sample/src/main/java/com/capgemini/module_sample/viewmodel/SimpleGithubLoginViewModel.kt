package com.capgemini.module_sample.viewmodel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capgemini.lib_communicate.sp.ProfileInfo
import com.capgemini.module_sample.BuildConfig
import com.capgemini.repository.SampleRepository1
import kotlinx.coroutines.launch

class SimpleGithubLoginViewModel @ViewModelInject constructor(private val repository1: SampleRepository1) :
        ViewModel() {

    fun getToken(){
        viewModelScope.launch {
            val response = repository1.getGithubToken(BuildConfig.CLIENT_ID,BuildConfig.CLIENT_SECRET,ProfileInfo.code)
            ProfileInfo.accessToken = response.accessToken
            Log.d("=====token",ProfileInfo.accessToken)
        }
    }
}