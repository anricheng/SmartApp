package com.capgemini.module_sample.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.capgemini.repository.SampleRepository1

class SimpleReposDetailsViewModel @ViewModelInject constructor(private val repository1: SampleRepository1) : ViewModel(){



}