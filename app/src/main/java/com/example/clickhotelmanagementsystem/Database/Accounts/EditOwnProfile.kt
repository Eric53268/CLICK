package com.example.clickhotelmanagementsystem.Database.Accounts

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

//Database entity class
@Parcelize
@Entity(tableName = "Accounts")
data class EditOwnProfile(
    @NonNull
    @PrimaryKey (autoGenerate = true) val uID: Int,
    @ColumnInfo(name = "first_name") var firstName: String?,
    @ColumnInfo(name = "last_name") var lastName: String?,
    val DOB: String?,
    val address: String?,
    var IC: Int,
    var email: String?,
    var department: String?,
    var position: String?,
    var phoneNumber: String?,
    var password: String?
): Parcelable