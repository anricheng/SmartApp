package com.capgemini.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.capgemini.entity.ItemEntity

@Dao
interface SimpleEntityDao {

    /**
     * Observes list of SIMPLE_DATA.
     *
     * @return all SIMPLE_DATA.
     */
    @Query("SELECT * FROM SIMPLE_DATA")
    fun observeData(): LiveData<List<ItemEntity>>

    /**
     * Observes a single task.
     *
     * @param taskId the task id.
     * @return the task with taskId.
     */
    @Query("SELECT * FROM SIMPLE_DATA WHERE guid = :taskId")
    fun observeTaskById(taskId: String): LiveData<ItemEntity>

    /**
     * Select all SIMPLE_DATA from the SIMPLE_DATA table.
     *
     * @return all SIMPLE_DATA.
     */
    @Query("SELECT * FROM SIMPLE_DATA")
    suspend fun getData(): List<ItemEntity>

    /**
     * Select a task by id.
     *
     * @param taskId the task id.
     * @return the task with taskId.
     */
    @Query("SELECT * FROM SIMPLE_DATA WHERE guid = :taskId")
    suspend fun getDataById(taskId: String): ItemEntity?

    /**
     * Insert a task in the database. If the task already exists, replace it.
     *
     * @param task the task to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(task: ItemEntity)

    /**
     * Update a task.
     *
     * @param task task to be updated
     * @return the number of SIMPLE_DATA updated. This should always be 1.
     */
    @Update
    suspend fun updateData(task: ItemEntity): Int

    /**
     * Update the complete status of a task
     *
     * @param taskId id of the task
     * @param completed status to be updated
     */
    @Query("UPDATE SIMPLE_DATA SET content = :content WHERE guid = :taskId")
    suspend fun updateCompleted(taskId: String, content: String)

    /**
     * Delete a task by id.
     *
     * @return the number of SIMPLE_DATA deleted. This should always be 1.
     */
    @Query("DELETE FROM SIMPLE_DATA WHERE guid = :taskId")
    suspend fun deleteTaskById(taskId: String): Int

    /**
     * Delete all SIMPLE_DATA.
     */
    @Query("DELETE FROM SIMPLE_DATA")
    suspend fun deleteData()

    /**
     * Delete all completed SIMPLE_DATA from the table.
     *
     * @return the number of SIMPLE_DATA deleted.
     */
    @Query("DELETE FROM SIMPLE_DATA WHERE isUsed = 1")
    suspend fun deleteUsedData(): Int
}
