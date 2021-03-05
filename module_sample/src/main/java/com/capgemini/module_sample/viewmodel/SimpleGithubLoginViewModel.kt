package com.capgemini.module_sample.viewmodel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capgemini.api.GitHubToken
import com.capgemini.lib_communicate.sp.ProfileInfo
import com.capgemini.module_sample.BuildConfig
import com.capgemini.repository.SampleRepository1
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SimpleGithubLoginViewModel @ViewModelInject constructor(private val repository1: SampleRepository1) :
        ViewModel() {

    fun getToken(){

        val retrofit = Retrofit.Builder().baseUrl(BuildConfig.GITHUB_TOKEN).addConverterFactory(GsonConverterFactory.create()).build()
        val api = retrofit.create(GitHubToken::class.java)

        viewModelScope.launch {
            val response = repository1.getGithubToken("264b448455ce3858fd75","b04ce727b25916355e8c0a083f58b4a832f79fd6",ProfileInfo.accessToken)
            //val response = api.getToken("264b448455ce3858fd75","b04ce727b25916355e8c0a083f58b4a832f79fd6",ProfileInfo.accessToken)
            val responseString =response.body().toString()

        }
    }
}