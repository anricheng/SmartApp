package com.capgemini.module_sample.viewmodel

import android.os.Bundle
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alibaba.android.arouter.launcher.ARouter
import com.capgemini.entity.ReposPullRequestItem
import com.capgemini.entity.RepositoriesItem
import com.capgemini.lib_common.extendtions.hideLoading
import com.capgemini.lib_common.extendtions.showLoading
import com.capgemini.lib_communicate.arouter.NavigationHelper
import com.capgemini.lib_communicate.arouter.SampleModuleARouterPath
import com.capgemini.module_sample.R
import com.capgemini.repository.SampleRepository1
import kotlinx.coroutines.launch

class SimpleReposPullRequestModel @ViewModelInject constructor(private val sampleRepository1: SampleRepository1) :
    ViewModel() {
    private val _dataList = MutableLiveData<List<ReposPullRequestItem>>()
    val dataList: LiveData<List<ReposPullRequestItem>> = _dataList
    val pullRequestData = MutableLiveData<ReposPullRequestItem>()
    var bundle:Bundle? = null
    var visibility = MutableLiveData<Boolean>()

    fun getpullRequestData(ownername:String,reponame:String){
        viewModelScope.launch {
            showLoading(R.string.simple_loading_context_pullrequest)
           val data = sampleRepository1.getReposPullRequest(ownername,reponame)
            pullRequestData.value = data.get(0)
            _dataList.value = data
            hideLoading()
            visibility.value = true
        }
    }

}