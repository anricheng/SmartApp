package com.capgemini.module_sample.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capgemini.entity.GithubUser
import com.capgemini.entity.RecentEvent
import com.capgemini.entity.RepositoriesItem
import com.capgemini.lib_common.extendtions.hideLoading
import com.capgemini.lib_common.extendtions.showLoading
import com.capgemini.module_sample.R
import com.capgemini.repository.SampleRepository1
import kotlinx.coroutines.launch

class SimpleRecentEventViewModel @ViewModelInject constructor(private val repository1: SampleRepository1) : ViewModel(){

    var visibility = MutableLiveData<Boolean>()

    var data = MutableLiveData<GithubUser>()

    private var _recentEventData = MutableLiveData<List<RecentEvent>>()
    val dataList: LiveData<List<RecentEvent>> = _recentEventData

    fun getUser(){
        viewModelScope.launch {
            showLoading(R.string.simple_loading)
            data.value = repository1.getGithubUser("willdurand")
            hideLoading()
        }
    }

    fun getRecentEvent(){
        viewModelScope.launch {
            showLoading(R.string.simple_loading)
            _recentEventData.value = repository1.getRecentEvent("willdurand")
            hideLoading()
            visibility.value = true
        }
    }

}