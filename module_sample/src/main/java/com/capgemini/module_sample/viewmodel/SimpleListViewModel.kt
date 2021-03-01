package com.capgemini.module_sample.viewmodel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capgemini.entity.ItemEntity
import com.capgemini.repository.SampleRepository1
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicInteger

class SimpleListViewModel @ViewModelInject constructor(private val repository1: SampleRepository1) :
    ViewModel() {
    private val count = AtomicInteger(0)
    val dataList: LiveData<List<ItemEntity>> = repository1.getTask()

    fun createData() {
        viewModelScope.launch {
            Log.d("aric",Thread.currentThread().name)
            repository1.insertData(ItemEntity(content = "这是第${count.incrementAndGet()}条数据"))
        }
    }

    fun createUser() {
        viewModelScope.launch {
            Log.d("aric",Thread.currentThread().name)
            repository1.insertData(ItemEntity(content = "这是第${count.incrementAndGet()}条数据"))
        }
    }
}