package com.example.clickhotelmanagementsystem.Database.Reservations

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

//Database entity class
@Parcelize
@Entity(tableName = "Reservations")
data class CustomerDetails(
    @NonNull
    @PrimaryKey(autoGenerate = true) var roomID: Int,
    val reservationName: String?,
    val pax: Int?,
    val breakfast: Boolean?,
    val checkInDate: String?,
    val checkOutDate: String?
    //val bookingMethod: Boolean
): Parcelable