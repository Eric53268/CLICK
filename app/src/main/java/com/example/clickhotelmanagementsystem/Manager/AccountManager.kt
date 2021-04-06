package com.example.clickhotelmanagementsystem.Manager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.clickhotelmanagementsystem.R
import com.example.clickhotelmanagementsystem.databinding.ActivityAccountManagerBinding
import kotlinx.android.synthetic.main.activity_account_manager.*

class AccountManager : AppCompatActivity() {

    private lateinit var binding: ActivityAccountManagerBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityAccountManagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = findNavController(R.id.add_list_user)
        drawerLayout = findViewById(R.id.drawerLayout)
        nav_menu.setupWithNavController(navController)
        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)

        /*setSupportActionBar(toolbar)

        toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close)
        toggle.isDrawerIndicatorEnabled = true
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()*/

    }

    override fun onSupportNavigateUp(): Boolean {
        navController = findNavController(R.id.add_list_user)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    /*    override fun onBackPressed() {
        if (nav_menu.isDrawerOpen(GravityCompat.START)) {
            nav_menu.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }*/

}

/*
package com.example.clickhotelmanagementsystem.Manager

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.clickhotelmanagementsystem.R
import com.example.clickhotelmanagementsystem.databinding.ActivityAccountManagerBinding
import kotlinx.android.synthetic.main.activity_account_manager.*
import kotlinx.android.synthetic.main.content_main.*

class AccountManager : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityAccountManagerBinding

    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityAccountManagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = findNavController(R.id.add_list_user)
        drawerLayout = binding.drawerLayout
        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
        nav_menu.setupWithNavController(navController)
        setupActionBarWithNavController(navController, appBarConfiguration)

        //supportActionBar?.setBackgroundDrawable(ColorDrawable(getColor(R.color.white)))

        setSupportActionBar(toolbar)
        toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close)
        toggle.isDrawerIndicatorEnabled = true
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        nav_menu.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.activity_account_manager -> Toast.makeText(
                    applicationContext,
                    "Clicked on Staff Details", Toast.LENGTH_LONG).show()
                R.id.activity_reservations -> Toast.makeText(
                    applicationContext,
                    "Clicked on Staff Details", Toast.LENGTH_LONG).show()
            }
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}*/