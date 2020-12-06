package com.capgemini.module_sample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capgemini.entity.ItemData

class SimpleListViewModel : ViewModel() {

    private val _data = MutableLiveData<MutableList<ItemData>>()

    val data: LiveData<MutableList<ItemData>> = _data

    fun createData() {
        val list = ArrayList<ItemData>(30)
        for (i in 0..30) {
            list.add(ItemData(content = "这是第${i}个数据"))
        }
        _data.value = list
    }
}