package com.example.tasks.repository

import androidx.lifecycle.LiveData
import com.example.tasks.database.TaskDao
import com.example.tasks.model.Task

class TaskRepository(private val taskDao: TaskDao) {

    val readAllData: LiveData<List<Task>> = taskDao.readAllData()
    //val readTaskData: LiveData<List<Task>> = taskDao.readTaskData()

    suspend fun addTask(task: Task) {
        taskDao.addTask((task))
    }

    suspend fun updateTask(task: Task) {
        taskDao.updateTask(task)
    }

    suspend fun deleteTask(task: Task) {
        taskDao.deleteTask(task)
    }

    fun searchDatabase(searchQuery: String): LiveData<List<Task>> {
        return taskDao.searchDatabase(searchQuery)
    }

    fun readParticularData(name: String): LiveData<List<Task>> {
        return taskDao.readParticularData(name)
    }
}