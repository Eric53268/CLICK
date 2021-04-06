package com.example.tasks

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupActionBarWithNavController(findNavController(R.id.fragment))
    }

    override fun onSupportNavigateUp(): Boolean {
        return (findNavController(R.id.fragment)).navigateUp() || super.onSupportNavigateUp()
    }

    /*@Suppress("UNUSED_PARAMETER")
    fun cancel(v: View) {
        val intent = Intent(this, StaffActivity::class.java)
        startActivity(intent)
    }*/

}