package com.example.clickhotelmanagementsystem.DTO

import com.example.clickhotelmanagementsystem.DTO.EventItem

class Event {

    var id: Long = -1
    var name = ""
    var createdAt = ""
    var items: MutableList<EventItem> = ArrayList()

}