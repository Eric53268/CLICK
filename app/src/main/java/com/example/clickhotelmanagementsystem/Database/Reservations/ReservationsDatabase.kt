package com.example.clickhotelmanagementsystem.Database.Reservations

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CustomerDetails::class], version = 1, exportSchema = false)
abstract class ReservationsDatabase: RoomDatabase() {

    abstract fun reservationsDAO(): ReservationsDAO

    companion object{
        @Volatile
        private var INSTANCE: ReservationsDatabase? = null

        fun getDatabase(context: Context): ReservationsDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ReservationsDatabase::class.java,
                    "reservations_database"
                ).build()
                INSTANCE = instance
                return instance
            }

        }
    }

}