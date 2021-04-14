package com.example.clickhotelmanagementsystem.Database.Reservations

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ReservationsViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<CustomerDetails>>
    private val repository: ReservationsRepository

    init{
        val reservationsDAO = ReservationsDatabase.getDatabase(application).reservationsDAO()
        repository = ReservationsRepository(reservationsDAO)
        readAllData = repository.readAllData
    }

    fun addReservation(reservation: CustomerDetails){
        viewModelScope.launch(Dispatchers.IO){
            repository.addReservation(reservation)
        }
    }

    fun updateReservation(reservation: CustomerDetails){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateReservation(reservation)
        }
    }

    fun deleteReservation(reservation: CustomerDetails){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteReservation(reservation)
        }
    }

    fun deleteAllReservations(reservation: CustomerDetails){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAllReservations()
        }
    }

}