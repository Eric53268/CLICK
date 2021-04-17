package com.example.clickhotelmanagementsystem.Database.Reservations

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [CustomerDetails::class], version = 2, exportSchema = false)
abstract class ReservationsDatabase: RoomDatabase() {

    abstract fun reservationsDAO(): ReservationsDAO

    companion object{
        @Volatile
        private var INSTANCE: ReservationsDatabase? = null

        private val migration_1_2: Migration = object: Migration(1, 2){
            override fun migrate(database: SupportSQLiteDatabase){
                database.run { execSQL("""ALTER TABLE 'Reservations' ADD COLUMN 'roomType' DEFAULT""") }
            }
        }

        fun getDatabase(context: Context): ReservationsDatabase{
            var tempInstance = INSTANCE
            if(tempInstance == null){
                tempInstance = Room.databaseBuilder(
                    context.applicationContext, ReservationsDatabase::class.java, "Reservations"
                )
                    .addMigrations(MIGRATION_1_2)
                    .build()
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