package com.example.clickhotelmanagementsystem.Staff

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.clickhotelmanagementsystem.R
import com.example.tasks.StaffActivity

class MainPageStaff : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page_staff)

        val task: Button = findViewById(R.id.tasks)
        task.setOnClickListener {
            val intent = Intent(this, StaffActivity::class.java)
            startActivity(intent)
        }
    }
}