package com.capgemini.repository

import androidx.lifecycle.LiveData
import com.capgemini.api.GithubApi
import com.capgemini.database.dao.SimpleEntityDao
import com.capgemini.entity.ItemEntity

class SampleRepository1(private val dao: SimpleEntityDao, private val githubApi: GithubApi) {

    fun getTask(): LiveData<List<ItemEntity>> {
        return dao.observeData()
    }

    suspend fun insertData(data: ItemEntity) {
        dao.insertData(data)
    }

    suspend fun getReposDetails(owner: String,repo: String) = githubApi.getReposDetails(owner,repo)
}