package com.example.clickhotelmanagementsystem

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.clickhotelmanagementsystem.Manager.MainPageManager
import com.example.clickhotelmanagementsystem.databinding.ActivityLoginPageBinding
import kotlinx.android.synthetic.main.activity_login_page.*

class LoginPage : AppCompatActivity() {

    private lateinit var binding: ActivityLoginPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var inputValid = false
        val loginIdInput = loginIDInput.text.toString()
        val passwordInput = passwordInput.text.toString()

        /*fun loginCheck(argLoginIdInput: String, argPasswordInput: String): Boolean {
            if(!(isEmpty(argLoginIdInput))){
                inputValid = true
            }
            else if(!(isEmpty(argPasswordInput))){
                inputValid = true
            }
            return inputValid

        }*/

        binding.loginButton.setOnClickListener {
            val intent = Intent(this, MainPageManager::class.java)
            startActivity(intent)
        }

        /*binding.loginButton.setOnClickListener {

            Toast.makeText(this, "Nub!", Toast.LENGTH_LONG)
            if(loginCheck(loginIdInput, passwordInput)){
                val intent = Intent(this, MainPageManager::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(this, "Nub!", Toast.LENGTH_LONG)
            }

        }*/

        //Validation V3
        /*if((isEmpty(loginIdInput))){
            Toast.makeText(this, "Nub!", Toast.LENGTH_LONG)
        }
        else if((isEmpty(passwordInput))){
            Toast.makeText(this, "Nub!", Toast.LENGTH_LONG)
        }
        else{
            val intent = Intent(this, MainPageManager::class.java)
            startActivity(intent)
        }*/

        //Validation V2
        /*if(!(isEmpty(loginIdInput))){

            if(loginIdInput.isDigitsOnly()){
                Toast.makeText(this, "ID is valid!", Toast.LENGTH_LONG).show()
                val intent = Intent(this, MainPageManager::class.java)
                startActivity(intent)
            }
        }else if (!(isEmpty(passwordInput))){
            if(passwordInput.isDigitsOnly()){
                Toast.makeText(this, "Password is valid!", Toast.LENGTH_LONG).show()
            }
        }
        else{
            Toast.makeText(this, "Nub!", Toast.LENGTH_LONG)
        }*/

        //Validation V1
        /*if(!(isEmpty(loginIdInput))){
            if(loginIdInput.isDigitsOnly()){
                Toast.makeText(this, "ID is valid!", Toast.LENGTH_LONG).show()
                val intent = Intent(this, MainPageManager::class.java)
                startActivity(intent)
            }
        }else{
            Toast.makeText(this, "Nub!", Toast.LENGTH_LONG)
        }

        if(!(isEmpty(passwordInput))){
            Toast.makeText(this, "ID is valid!", Toast.LENGTH_LONG).show()
            val intent = Intent(this, MainPageManager::class.java)
            startActivity(intent)
        }else{
            Toast.makeText(this, "Nub!", Toast.LENGTH_LONG)
        }*/

    }

}