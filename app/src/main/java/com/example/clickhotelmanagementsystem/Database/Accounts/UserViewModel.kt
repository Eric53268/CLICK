package com.example.clickhotelmanagementsystem.Database.Accounts

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//Provide data to UI and survive configuration changes. Acts as communication center between Repository and UI
class UserViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<EditOwnProfile>>
    private val repository: UserRepository

    init{
        val userDAO = UserDatabase.getDatabase(application).userDAO()
        repository = UserRepository(userDAO)
        readAllData = repository.readAllData
    }

    fun addUser(user: EditOwnProfile){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }

    fun updateUser(user: EditOwnProfile){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateUser(user)
        }
    }

    fun deleteUser(user: EditOwnProfile){
        viewModelScope.launch (Dispatchers.IO){
            repository.deleteUser(user)
        }
    }

    fun deleteAllUsers(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAllUsers()
        }
    }

    /*fun getUserPosition(id: Int){
        viewModelScope.launch(Dispatchers.IO){
            repository.getUserPosition(id)
        }
    }

    fun loadPSW(id: Int){
        viewModelScope.launch(Dispatchers.IO){
            repository.loadPSW(id)
        }
    }

    fun getPosition(user: EditOwnProfile){
        viewModelScope.launch(Dispatchers.IO){
            repository.getPosition(user)
        }
    }

    fun isExist(id: Int){
        viewModelScope.launch(Dispatchers.IO){
            repository.isExist(id)
        }
    }*/

}