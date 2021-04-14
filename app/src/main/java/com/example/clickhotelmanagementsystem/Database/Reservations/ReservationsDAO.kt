package com.example.clickhotelmanagementsystem.Database.Reservations

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ReservationsDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addReservation(reservation: CustomerDetails)

    @Update
    suspend fun updateReservation(reservation: CustomerDetails)

    @Delete
    suspend fun deleteReservation(reservation: CustomerDetails)

    //Delete all
    @Query("DELETE FROM Reservations")
    suspend fun deleteAllReservations()

    @Query("SELECT * FROM Reservations ORDER BY roomID ASC")
    fun readAllData(): LiveData<List<CustomerDetails>>

}