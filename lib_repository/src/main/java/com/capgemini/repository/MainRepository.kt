package com.capgemini.repository

import com.capgemini.database.entity.TaskEntity
import com.capgemini.database.dao.TasksDao

class MainRepository(private val dao: TasksDao) {

    suspend fun getTask(taskId: String):List<TaskEntity>{
       return dao.getTasks()
    }

}