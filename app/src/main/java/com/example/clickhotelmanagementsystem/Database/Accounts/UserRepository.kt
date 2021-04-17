package com.example.clickhotelmanagementsystem.Database.Accounts

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

    suspend fun deleteUser(user: EditOwnProfile){
        userDAO.deleteUser(user)
    }

    suspend fun deleteAllUsers(){
        userDAO.deleteAllUsers()
    }

}