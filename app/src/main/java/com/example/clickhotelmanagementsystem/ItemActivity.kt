package com.example.clickhotelmanagementsystem

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.clickhotelmanagementsystem.DTO.EventItem
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ItemActivity : AppCompatActivity() {
    lateinit var dbHandler: DBHandler
    var eventId : Long = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)
        val tb : Toolbar = findViewById(R.id.item_toolbar)
        setSupportActionBar(tb)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.title = intent.getStringExtra(INTENT_EVENT_NAME)
        eventId = intent.getLongExtra(INTENT_EVENT_ID,-1)
        dbHandler = DBHandler(this)

        val rv : RecyclerView = findViewById(R.id.rv_item)
        rv.layoutManager = LinearLayoutManager(this)

        val floating1 : FloatingActionButton = findViewById(R.id.fab_item)
        floating1.setOnClickListener {
            val dialog  = AlertDialog.Builder(this)
            dialog.setTitle("Add event Task")
            val view = layoutInflater.inflate(R.layout.dialog_dashboard, null)
            val eventName = view.findViewById<EditText > (R.id.ev_event)
            dialog.setView(view)
            dialog.setPositiveButton("Add") { _: DialogInterface, _: Int ->
                if (eventName.text.isNotEmpty()) {
                    val item = EventItem()
                    item.eventName = eventName.text.toString()
                    item.eventId = eventId
                    item.isCompleted = false
                    dbHandler.addEventItem(item)
                    refreshList()
                }
            }
            dialog.setNegativeButton("Cancel") { _: DialogInterface, _: Int ->

            }
            dialog.show()
        }
    }
    fun updateItem(item :EventItem){
        val dialog  = AlertDialog.Builder(this)
        dialog.setTitle("Edit Event Task")
        val view = layoutInflater.inflate(R.layout.dialog_dashboard, null)
        val eventName = view.findViewById<EditText > (R.id.ev_event)
        eventName.setText(item.eventName)
        dialog.setView(view)
        dialog.setPositiveButton("edit") { _: DialogInterface, _: Int ->
            if (eventName.text.isNotEmpty()) {
                item.eventName = eventName.text.toString()
                item.eventId = eventId
                item.isCompleted = false
                dbHandler.updateEventItem(item)
                refreshList()
            }
        }
        dialog.setNegativeButton("Cancel") { _: DialogInterface, _: Int ->

        }
        dialog.show()

    }
    override fun onResume() {
        refreshList()
        super.onResume()
    }

    private fun refreshList(){
        val  rv : RecyclerView = findViewById(R.id.rv_item)
        rv.adapter = ItemAdapter(this,dbHandler.getEventItem(eventId))
    }

    class ItemAdapter(val activity: ItemActivity, val list: MutableList<EventItem>) :
            RecyclerView.Adapter<ItemAdapter.ViewHolder>() {
        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
            return ViewHolder(LayoutInflater.from(activity).inflate(R.layout.rv_child_item, p0, false))
        }

        override fun getItemCount(): Int {
            return list.size
        }

        override fun onBindViewHolder(holder: ViewHolder, p1: Int) {
            holder.itemName.text = list[p1].eventName
            holder.itemName.isChecked = list[p1].isCompleted
            holder.itemName.setOnClickListener {
                list[p1].isCompleted = !list[p1].isCompleted
                activity.dbHandler.updateEventItem(list[p1])
            }
            holder.delete.setOnClickListener {
                val dialog = AlertDialog.Builder(activity)
                dialog.setTitle("Are you sure")
                dialog.setMessage("Do you want to delete this event task?")
                dialog.setPositiveButton("Confirm delete") { _: DialogInterface, _: Int ->
                    activity.dbHandler.deleteEventItem(list[p1].id)
                    activity.refreshList()
                }
                dialog.setNegativeButton("Cancel") { _: DialogInterface, _: Int ->
                }
                dialog.show()
            }
            holder.edit.setOnClickListener{
                activity.updateItem(list[p1])
                activity.refreshList()
            }
        }

        class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
            val itemName: CheckBox = v.findViewById(R.id.cb_item)
            val edit: ImageView = v.findViewById(R.id.iv_edit)
            val delete: ImageView = v.findViewById(R.id.iv_delete)
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == android.R.id.home){
            finish()
            true
        }else
            super.onOptionsItemSelected(item)
    }
}