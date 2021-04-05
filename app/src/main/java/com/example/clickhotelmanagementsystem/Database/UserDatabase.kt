package com.example.clickhotelmanagementsystem.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [EditOwnProfile::class], version = 1, exportSchema = false)
//Contains the database holder and serves as the main access point for the underlying connection to your app's persisted, relational data
abstract class UserDatabase: RoomDatabase() {

    abstract fun userDAO(): UserDAO

    companion object{
        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getDatabase(context: Context):UserDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}