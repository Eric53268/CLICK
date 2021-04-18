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
import com.example.clickhotelmanagementsystem.databinding.ActivityReservationsBinding
import kotlinx.android.synthetic.main.activity_reservations.*


class Reservations : AppCompatActivity()/*, NavigationView.OnNavigationItemSelectedListener */{

    private lateinit var binding: ActivityReservationsBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityReservationsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*navController = findNavController(R.id.view_all_reservations)
        drawerLayout = findViewById(R.id.drawerLayout)
        nav_menu.setupWithNavController(navController)
        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)*/

        /*        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(
                R.id.view_all_reservations,
                ViewAllReservations()
            ).commit()
            nav_menu.setCheckedItem(R.id.view_all_reservations)
        }

        drawer = binding.drawerLayout
        var navigationView = findViewById<NavigationView>(R.id.nav_menu)
        val toggle = ActionBarDrawerToggle(
            this, drawer, this.toolbar,
            R.string.open, R.string.close
        )
        drawer!!.addDrawerListener(toggle)
        toggle.syncState()
        replaceFragment(view_all_reservations)
        nav_menu.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.staffDetailsNav -> replaceFragment(view_all_reservations)
            }
            true
        }*/

    }

    /*private fun replaceFragment(fragment: Fragment){
            if(fragment == null){
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.view_all_reservations, fragment)
                transaction.commit()
            }
    }*/

    //private var drawer: DrawerLayout? = null

    /*override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.staffDetailsNav -> replaceFragment(add_list_user)
        }
        drawer?.closeDrawer(GravityCompat.START)
        return true
    }*/

    /*override fun onSupportNavigateUp(): Boolean {
        navController = findNavController(R.id.view_all_reservations)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }*/

}