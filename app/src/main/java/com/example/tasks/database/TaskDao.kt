package com.example.tasks.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.tasks.model.Task

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Query("SELECT * FROM task_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Task>>

    //@Query("SELECT taskDescription FROM task_table ORDER BY id ASC")
    //fun readTaskData(): LiveData<List<Task>>

    @Query("SELECT * FROM task_table WHERE taskDescription LIKE :searchQuery OR staffName LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): LiveData<List<Task>>
}