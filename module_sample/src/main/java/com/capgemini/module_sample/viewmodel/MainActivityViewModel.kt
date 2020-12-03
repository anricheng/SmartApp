package com.capgemini.module_sample.viewmodel

import android.text.Editable
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capgemini.database.entity.TaskEntity
import com.capgemini.lib_common.utils.SimpleTextWatcher
import com.capgemini.repository.MainRepository
import kotlinx.coroutines.launch

class MainActivityViewModel @ViewModelInject constructor(private val mainRepository: MainRepository) : ViewModel(){

    private val _task = MutableLiveData<List<TaskEntity>>()
    val task: LiveData<List<TaskEntity>> = _task

    //two-way binding
    val taskId = MutableLiveData<String>("")

    val textWatcher = object : SimpleTextWatcher() {
        override fun afterTextChanged(s: Editable) {
            viewModelScope.launch {
                mainRepository.getTask(taskId.value!!)
            }
        }
    }
}

