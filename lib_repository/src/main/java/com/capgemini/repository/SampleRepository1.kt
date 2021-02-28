package com.capgemini.repository

import androidx.lifecycle.LiveData
import com.capgemini.api.ReposDetailsApi
import com.capgemini.database.dao.SimpleEntityDao
import com.capgemini.entity.ItemEntity
import com.capgemini.entity.ReposDetails

class SampleRepository1(private val dao: SimpleEntityDao, private val reposDetailsApi: ReposDetailsApi) {

    fun getTask(): LiveData<List<ItemEntity>> {
        return dao.observeData()
    }

    suspend fun insertData(data: ItemEntity) {
        dao.insertData(data)
    }

    suspend fun getReposDetails(owner: String,repo: String) = reposDetailsApi.getReposDetails(owner,repo)
}