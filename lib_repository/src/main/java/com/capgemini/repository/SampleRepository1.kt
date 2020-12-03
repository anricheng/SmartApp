package com.capgemini.repository

import com.capgemini.database.dao.TasksDao
import com.capgemini.entity.TaskEntity

class SampleRepository1(private val dao: TasksDao) {

    suspend fun getTask(taskId: String):List<TaskEntity>{
       return dao.getTasks()
    }

}