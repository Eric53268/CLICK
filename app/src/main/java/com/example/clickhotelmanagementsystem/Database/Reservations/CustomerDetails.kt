package com.example.clickhotelmanagementsystem.Database.Reservations

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.android.parcel.Parcelize

//Database entity class
@Parcelize
@Entity(tableName = "Reservations")
data class CustomerDetails(
    @NonNull
    @PrimaryKey(autoGenerate = true) var roomID: Int,
    val reservationName: String?,
    val pax: Int,
    val breakfast: Boolean,
    val checkInDate: String?,
    val checkOutDate: String?,
    val roomType: String?
    //val bookingMethod: Boolean
): Parcelable

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "ALTER TABLE Reservations ADD COLUMN roomType TEXT NOT NULL DEFAULT ''")
    }
}