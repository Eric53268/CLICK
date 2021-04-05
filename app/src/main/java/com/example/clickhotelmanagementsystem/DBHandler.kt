package com.example.clickhotelmanagementsystem

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.clickhotelmanagementsystem.DTO.EventItem
import com.example.clickhotelmanagementsystem.DTO.Event


class DBHandler(private val context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_version) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createEventTable = "  CREATE TABLE $TABLE_EVENT (" +
                "$COL_ID integer PRIMARY KEY AUTOINCREMENT," +
                "$COL_CREATED_AT datetime DEFAULT CURRENT_TIMESTAMP," +
                "$COL_NAME varchar);"

        val createEventItemTable =
            "CREATE TABLE $TABLE_EVENT_ITEM (" +
                    "$COL_ID integer PRIMARY KEY AUTOINCREMENT," +
                    "$COL_CREATED_AT datetime DEFAULT CURRENT_TIMESTAMP," +
                    "$COL_EVENT_ID integer," +
                    "$COL_EVENT_NAME varchar," +
                    "$COL_IS_COMPLETED integer);"


        db!!.execSQL(createEventTable)

        db.execSQL(createEventItemTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun addEvent(event: Event): Boolean{
        val db = writableDatabase
        val cv = ContentValues()
        cv.put(COL_NAME, event.name)
        val result = db.insert(TABLE_EVENT, null, cv)
        return result != (-1).toLong()
    }
    fun updateEvent(event: Event){
        val db = writableDatabase
        val cv = ContentValues()
        cv.put(COL_NAME, event.name)
        db.update(TABLE_EVENT, cv, "$COL_ID=?" , arrayOf(event.id.toString()))
    }

    fun getEvents() : MutableList<Event>{
        val result: MutableList<Event> = ArrayList()
        val db = readableDatabase
        val queryResult = db.rawQuery("SELECT * from $TABLE_EVENT", null)
        if (queryResult.moveToFirst()) {
            do {
                val todo = Event()
                todo.id = queryResult.getLong(queryResult.getColumnIndex(COL_ID))
                todo.name = queryResult.getString(queryResult.getColumnIndex(COL_NAME))
                result.add(todo)
            } while (queryResult.moveToNext())
        }
        queryResult.close()
        return result

    }

    fun addEventItem(item: EventItem): Boolean {

        val db = writableDatabase
        val cv = ContentValues()
        cv.put(COL_EVENT_NAME, item.eventName)
        cv.put(COL_EVENT_ID, item.eventId)
        cv.put(COL_IS_COMPLETED, item.isCompleted)


        val result = db.insert(TABLE_EVENT_ITEM, null, cv)
        return result != (-1).toLong()
    }

    fun deleteEvent(eventId: Long) {
        val db = writableDatabase
        db.delete(TABLE_EVENT, "$COL_ID=?", arrayOf(eventId.toString()))
    }


    fun updateEventItemCompletedStatus(eventId: Long, isCompleted: Boolean){
        val result: MutableList<EventItem> = ArrayList()

        val db = readableDatabase
        val queryResult = db.rawQuery("SELECT * FROM $TABLE_EVENT_ITEM WHERE $COL_EVENT_ID=$eventId", null)

        if (queryResult.moveToFirst()) {
            do {
                val item = EventItem()
                item.id = queryResult.getLong(queryResult.getColumnIndex(COL_ID))
                item.eventId = queryResult.getLong(queryResult.getColumnIndex(COL_EVENT_ID))
                item.eventName = queryResult.getString(queryResult.getColumnIndex(COL_EVENT_NAME))
                item.isCompleted = isCompleted
                updateEventItem(item)
            } while (queryResult.moveToNext())
        }
        queryResult.close()

    }

    fun updateEventItem(item: EventItem) {

        val db = writableDatabase
        val cv = ContentValues()
        cv.put(COL_EVENT_NAME, item.eventName)
        cv.put(COL_EVENT_ID, item.eventId)
        cv.put(COL_IS_COMPLETED, item.isCompleted)


        db.update(TABLE_EVENT_ITEM, cv, "$COL_ID=?", arrayOf(item.id.toString()))
    }
    fun deleteEventItem(itemId: Long){
        val db = writableDatabase
        db.delete(TABLE_EVENT_ITEM, "$COL_ID=?", arrayOf(itemId.toString()))

    }

    fun getEventItem(eventId: Long): MutableList<EventItem> {
        val result: MutableList<EventItem> = ArrayList()

        val db = readableDatabase
        val queryResult = db.rawQuery("SELECT * FROM $TABLE_EVENT_ITEM WHERE $COL_EVENT_ID=$eventId", null)

        if (queryResult.moveToFirst()) {
            do {
                val item = EventItem()
                item.id = queryResult.getLong(queryResult.getColumnIndex(COL_ID))
                item.eventId = queryResult.getLong(queryResult.getColumnIndex(COL_EVENT_ID))
                item.eventName = queryResult.getString(queryResult.getColumnIndex(COL_EVENT_NAME))
                item.isCompleted = queryResult.getInt(queryResult.getColumnIndex(COL_IS_COMPLETED)) == 1
                item.eventId = eventId
                result.add(item)
            } while (queryResult.moveToNext())
        }
        queryResult.close()
        return result
    }

}