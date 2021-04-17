package com.example.clickhotelmanagementsystem.Database.Reservations

import androidx.lifecycle.LiveData

class ReservationsRepository(private val reservationsDAO: ReservationsDAO) {

    val readAllData: LiveData<List<CustomerDetails>> = reservationsDAO.readAllData()

    suspend fun addReservation(reservation: CustomerDetails){
        reservationsDAO.addReservation(reservation)
    }

    suspend fun updateReservation(reservation: CustomerDetails){
        reservationsDAO.updateReservation(reservation)
    }

    suspend fun deleteReservation(reservation: CustomerDetails){
        reservationsDAO.deleteReservation(reservation)
    }

    suspend fun deleteAllReservations(){
        reservationsDAO.deleteAllReservations()
    }

}