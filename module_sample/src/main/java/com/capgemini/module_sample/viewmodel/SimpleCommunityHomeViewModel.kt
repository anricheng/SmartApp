package com.capgemini.module_sample.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.capgemini.repository.SampleRepository2

class SimpleCommunityHomeViewModel @ViewModelInject constructor(private val sampleRepository2: SampleRepository2) : ViewModel(){

    val feedlist = liveData {
        emit(sampleRepository2.getFeeds("", "", "", ""))
    }
    var map:MutableMap<Int, Int> = mutableMapOf<Int, Int>()
}




