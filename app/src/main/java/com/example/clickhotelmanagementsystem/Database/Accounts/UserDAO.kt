package com.example.clickhotelmanagementsystem.Database.Accounts

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
//Contains methods for accessing the database
interface UserDAO {

    @Insert
    suspend fun insert(user: EditOwnProfile)

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

    /*@Query("SELECT * FROM Accounts WHERE uID LIKE :uID")
    fun getUserPosition(uID: Int): EditOwnProfile?

    @Query("SELECT password FROM Accounts WHERE uID=:id")
    fun loadPSW(id: Int)

    @Query("SELECT position FROM Accounts WHERE uID=:id")
    fun getPosition(id: Int)

    @Query("SELECT COUNT(uID) FROM Accounts WHERE uID=:id")
    fun isExist(id: Int)*/

    @Query("SELECT * FROM Accounts WHERE uID=(:userID) AND password=(:password)")
    suspend fun login(userID: Int, password: String): EditOwnProfile

}