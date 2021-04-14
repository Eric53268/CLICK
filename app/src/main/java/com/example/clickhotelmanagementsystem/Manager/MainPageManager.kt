package com.example.clickhotelmanagementsystem.Manager

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.clickhotelmanagementsystem.DashboardActivity
import com.example.clickhotelmanagementsystem.R

class MainPageManager : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page_manager)

        val staffDetailsButton: Button = findViewById(R.id.staffDetails)

        staffDetailsButton.setOnClickListener{
            val intent = Intent(this, AccountManager::class.java)
            startActivity(intent)
        }

        val reservationsButton: Button = findViewById(R.id.reservations)

        reservationsButton.setOnClickListener{
            val intent = Intent(this, Reservations::class.java)
            startActivity(intent)
        }

        val event :  Button = findViewById(R.id.events)
        event.setOnClickListener{
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
        }


    }
}