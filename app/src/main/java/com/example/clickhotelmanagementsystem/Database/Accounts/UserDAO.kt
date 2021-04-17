package com.example.clickhotelmanagementsystem.Database.Accounts

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
//Contains methods for accessing the database
interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: EditOwnProfile)

    @Update
    suspend fun updateUser(user: EditOwnProfile)

    @Delete
    suspend fun deleteUser(user: EditOwnProfile)

    //Delete all
    @Query("DELETE FROM Accounts")
    suspend fun deleteAllUsers()

    @Query("SELECT * FROM Accounts ORDER BY uID ASC")
    fun readAllData(): LiveData<List<EditOwnProfile>>

}