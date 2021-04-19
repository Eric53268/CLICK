package com.example.clickhotelmanagementsystem.Manager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.ui.AppBarConfiguration
import com.example.clickhotelmanagementsystem.databinding.ActivityAccountManagerBinding

class AccountManager : AppCompatActivity() {

    private lateinit var binding: ActivityAccountManagerBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityAccountManagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*navController = findNavController(R.id.add_list_user)
        drawerLayout = findViewById(R.id.drawerLayout)
        nav_menu.setupWithNavController(navController)
        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)*/

    }

    /*override fun onSupportNavigateUp(): Boolean {
        navController = findNavController(R.id.add_list_user)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }*/

}