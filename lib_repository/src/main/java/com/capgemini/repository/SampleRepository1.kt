package com.capgemini.repository

import androidx.lifecycle.LiveData
import com.capgemini.api.GithubRestFulApi
import com.capgemini.database.dao.SimpleEntityDao
import com.capgemini.entity.ItemEntity
import com.capgemini.entity.UserInformation

class SampleRepository1(private val dao: SimpleEntityDao,private  val githubRestFulApi: GithubRestFulApi) {

    fun getTask(): LiveData<List<ItemEntity>> {
        return dao.observeData()
    }

    suspend fun insertData(data: ItemEntity) {
        dao.insertData(data)
    }

    suspend fun getRepositories(username:String) = githubRestFulApi.getRepositories(username)

    suspend fun getUserInformation(username:String) = githubRestFulApi.getUserImformation(username)
}