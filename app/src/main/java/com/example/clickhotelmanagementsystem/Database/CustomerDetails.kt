package com.example.clickhotelmanagementsystem.Database

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

//Database entity class
@Entity(tableName = "Reservations")
data class CustomerDetails(
    @NonNull
    @PrimaryKey(autoGenerate = true) var roomID: Int,
    val reservationName: String?,
    val pax: Int,
    val breakfast: Boolean,
    val checkInDate: Date,
    val checkOutDate: Date,
    val bookingMethod: Boolean
)