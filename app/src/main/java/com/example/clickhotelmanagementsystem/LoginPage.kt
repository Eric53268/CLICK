package com.example.clickhotelmanagementsystem

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.clickhotelmanagementsystem.Database.Accounts.UserDatabase
import com.example.clickhotelmanagementsystem.Database.Accounts.UserDatabase.Companion.getDatabase
import com.example.clickhotelmanagementsystem.Manager.MainPageManager
import com.example.clickhotelmanagementsystem.Staff.MainPageStaff
import com.example.clickhotelmanagementsystem.databinding.ActivityLoginPageBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginPage : AppCompatActivity() {

    private lateinit var binding: ActivityLoginPageBinding
    private lateinit var user: UserDatabase
    private lateinit var userId: EditText
    private lateinit var password: EditText
    private var loggedInAs: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //var inputValid = false

        /*fun loginCheck(argLoginIdInput: String, argPasswordInput: String): Boolean {
            if(!(isEmpty(argLoginIdInput))){
                inputValid = true
            }
            else if(!(isEmpty(argPasswordInput))){
                inputValid = true
            }
            return inputValid

        }*/
        //val application = requireNotNull(this).application
        /*val loginIdInput: Int = findViewById<TextView>(R.id.loginIDInput).text.toString().toInt()
        val passwordInput = passwordInput.text.toString()
        val intentManager = Intent(this, MainPageManager::class.java)
        val intentStaff = Intent(this, MainPageStaff::class.java)*/
        //binding.lifecycleOwner = this

        //Login Fragment
        /*loginViewModel.errorToast.observe(this, androidx.lifecycle.Observer { hasError->
            if(hasError==true){
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                loginViewModel.doneToast()
            }
        })*/

        userId = findViewById(R.id.loginIDInput)
        password = findViewById(R.id.passwordInput)
        val userIdText = userId.text
        val passwordText = password.text

        /*binding.loginRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.managerRadioButton -> {
                    loggedInAs = "Manager"
                }
                R.id.othersRadioButton -> {
                    loggedInAs = "Others"
                }
            }
        }*/

        binding.loginButton.setOnClickListener {
            /*lateinit var editOwnProfile: EditOwnProfile
            editOwnProfile.uID = Integer.parseInt(loginIDInput.text.toString())
            if (loginIdInput.toString() == mUserViewModel.isExist(loginIdInput).toString()){
                if(passwordInput == mUserViewModel.loadPSW(loginIdInput).toString()){
                    when(mUserViewModel.getUserPosition(loginIdInput).toString()){
                        "Manager" -> startActivity(intentManager)
                        else -> startActivity(intentStaff)
                    }
                }else{
                    Toast.makeText(this, "Password does not match", Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(this, "User does not exist", Toast.LENGTH_LONG).show()
            }*/
            if ((userIdText.toString() == "") || (passwordText.toString() == "")) {
                Toast.makeText(this, "Fill all fields!", Toast.LENGTH_LONG).show()
            } else {
                user = getDatabase(application)
                runOnUiThread(Runnable {
                    GlobalScope.launch {
                        run()
                    }
                })
            }
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

    /*@Override
    private suspend fun run() {
        val userDAO = user.userDAO()
        val editOwnProfile = userDAO.login(Integer.parseInt(userIdText.toString()), passwordText.toString())
        *//*if (editOwnProfile == null) {
            runOnUiThread {
                Toast.makeText(this, "Invalid credentials", Toast.LENGTH_LONG).show()
            }
        }else{
            val intent = Intent(this, MainPageManager::class.java)
            startActivity(intent)
        }*//*
        if (editOwnProfile != null) {
            val intent = Intent(this, MainPageManager::class.java)
            startActivity(intent)
        }else{
            runOnUiThread {
                Toast.makeText(this, "Invalid credentials", Toast.LENGTH_LONG).show()
            }
        }
    }*/

    @Override
    private suspend fun run() {
        val userIdText = userId.text
        val passwordText = password.text
        val userDAO = user.userDAO()
        val editOwnProfile = userDAO.login(
            Integer.parseInt(userIdText.toString()),
            passwordText.toString(),
        )
        /*if (editOwnProfile == null) {
            runOnUiThread {
                Toast.makeText(this, "Invalid credentials", Toast.LENGTH_LONG).show()
            }
        }else{
            val intent = Intent(this, MainPageManager::class.java)
            startActivity(intent)
        }*/
        if (editOwnProfile != null) {
            if (editOwnProfile.position == "Manager") {
                val intent = Intent(this, MainPageManager::class.java)
                startActivity(intent)
            } else if (editOwnProfile.position != "Manager") {
                val intent = Intent(this, MainPageStaff::class.java)
                startActivity(intent)
            } else {
                runOnUiThread {
                    Toast.makeText(this, "Invalid credentials", Toast.LENGTH_LONG).show()
                }
            }
        }

    }
}