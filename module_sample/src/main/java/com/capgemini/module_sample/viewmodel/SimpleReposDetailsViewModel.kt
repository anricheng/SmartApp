package com.capgemini.module_sample.viewmodel

import android.os.Bundle
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.capgemini.entity.ReposDetails
import com.capgemini.repository.SampleRepository1
import retrofit2.Response

class SimpleReposDetailsViewModel @ViewModelInject constructor(private val repository1: SampleRepository1) : ViewModel(){

//    lateinit var response: ReposDetails
//    suspend fun create(){
//        response= repository1.getReposDetails("anricheng","MyNote")
//        val bundle = Bundle()
//        bundle.putParcelable("reposDetails",response)
//    }


}