package com.capgemini.module_sample.viewmodel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.capgemini.entity.RepositoriesItem
import com.capgemini.lib_common.extendtions.hideLoading
import com.capgemini.lib_common.extendtions.showLoading
import com.capgemini.lib_communicate.arouter.NavigationHelper
import com.capgemini.lib_communicate.arouter.SampleModuleARouterPath
import com.capgemini.lib_communicate.arouter.SampleModuleARouterPath.Companion.SAMPLE_REPOS_DETAILS
import com.capgemini.module_sample.R
import com.capgemini.repository.SampleRepository1
import kotlinx.coroutines.launch

class SimpleReposViewModel @ViewModelInject constructor(private val repository1: SampleRepository1) :
        ViewModel() {

    private val _dataList = MutableLiveData<List<RepositoriesItem>>()
    val dataList: LiveData<List<RepositoriesItem>> = _dataList

    fun getData() {
        viewModelScope.launch {
            showLoading(R.string.simple_loading_context)
            val data = repository1.getRepositories("anricheng")
            _dataList.value = data
            hideLoading()
        }
    }
}