package com.example.tasks

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.clickhotelmanagementsystem.R

class StaffActivity : AppCompatActivity() {

    //private lateinit var staffViewModel: StaffViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_staff)

        /*staffViewModel = ViewModelProvider(this).get(StaffViewModel::class.java)
        staffViewModel.readFromDataStore.observe(this, {
            cb_complete.isChecked = it
        })

        save_status.setOnClickListener {
            val myName = cb_complete.isSelected
            staffViewModel.saveToDataStore(myName)
        }*/

        /*loadData()

        save_status.setOnClickListener {
            saveData()
        }*/
    }

    /*private fun saveData() {
        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply {
            putBoolean("BOOLEAN_KEY", cb_complete.isChecked)
        }.apply()

        Toast.makeText(this, "Task completed", Toast.LENGTH_SHORT).show()
    }

    private fun loadData() {
        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val savedBoolean = sharedPreferences.getBoolean("BOOLEAN_KEY", false)

        cb_complete.isChecked = savedBoolean
    }*/
}
