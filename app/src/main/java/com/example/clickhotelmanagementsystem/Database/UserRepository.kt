package com.example.clickhotelmanagementsystem.Database

import androidx.lifecycle.LiveData

//A repository class abstracts access to multiple data sources.
class UserRepository(private val userDAO: UserDAO) {

    val readAllData: LiveData<List<EditOwnProfile>> = userDAO.readAllData()

    suspend fun addUser(user: EditOwnProfile){
        userDAO.addUser(user)
    }

    suspend fun updateUser(user: EditOwnProfile){
        userDAO.updateUser(user)
    }

}