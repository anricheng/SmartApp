package com.capgemini.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.capgemini.database.dao.SimpleEntityDao
import com.capgemini.database.dao.TasksDao
import com.capgemini.entity.ItemEntity
import com.capgemini.entity.TaskEntity


@Database(entities = [TaskEntity::class, ItemEntity::class], version = 1, exportSchema = false)
abstract class SmartDataBase : RoomDatabase() {
    abstract fun taskDao(): TasksDao
    abstract fun simpleEntityDao(): SimpleEntityDao
}