package com.example.clickhotelmanagementsystem

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.clickhotelmanagementsystem.Manager.MainPageManager
import com.example.clickhotelmanagementsystem.databinding.ActivityLoginPageBinding

class LoginPage : AppCompatActivity() {

    private lateinit var binding: ActivityLoginPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //val loginButton: Button = (binding.loginButton)

        binding.loginButton.setOnClickListener {
            val intent = Intent(this, MainPageManager::class.java)
            startActivity(intent)
        }

    }

}