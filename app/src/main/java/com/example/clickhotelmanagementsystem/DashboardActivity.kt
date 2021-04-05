package com.example.clickhotelmanagementsystem

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.clickhotelmanagementsystem.DBHandler
import com.example.clickhotelmanagementsystem.DTO.Event
import com.example.clickhotelmanagementsystem.ItemActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton



class DashboardActivity : AppCompatActivity() {

    lateinit var dbHandler : DBHandler
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        dbHandler = DBHandler(this)
        val rv : RecyclerView = findViewById(R.id.rv_dashboard)
        rv.layoutManager = LinearLayoutManager(this)

        val floating1 : FloatingActionButton = findViewById(R.id.fab_dashboard)
        floating1.setOnClickListener {
            val dialog = AlertDialog.Builder(this)
            dialog.setTitle("Add new Event")
            val view = layoutInflater.inflate(R.layout.dialog_dashboard, null)
            val toDoName = view.findViewById<EditText>(R.id.ev_event)

            dialog.setView(view)
            dialog.setPositiveButton("Add") { _: DialogInterface, _: Int ->
                if (toDoName.text.isNotEmpty()) {
                    val toDo = Event()
                    toDo.name = toDoName.text.toString()
                    dbHandler.addEvent(toDo)
                    refreshList()
                }
            }
            dialog.setNegativeButton("Cancel") { _: DialogInterface, _: Int ->
            }
            dialog.show()
        }
    }

    override fun onResume(){
        refreshList()
        super.onResume()
    }

    fun updateEvent(event: Event){
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("Edit Event Name")
        val view = layoutInflater.inflate(R.layout.dialog_dashboard, null)
        val toDoName = view.findViewById<EditText>(R.id.ev_event)
        toDoName.setText(event.name)
        dialog.setView(view)
        dialog.setPositiveButton("Edit") { _: DialogInterface, _: Int ->
            if (toDoName.text.isNotEmpty()) {
                event.name = toDoName.text.toString()
                dbHandler.updateEvent(event)
                refreshList()
            }
        }
        dialog.setNegativeButton("Cancel") { _: DialogInterface, _: Int ->
        }
        dialog.show()

    }

    private fun refreshList(){
        val  rv : RecyclerView = findViewById(R.id.rv_dashboard)
        rv.adapter = DashboardAdapter(this, dbHandler.getEvents())
    }

    class DashboardAdapter(val activity: DashboardActivity, val list: MutableList<Event>) :
        RecyclerView.Adapter<DashboardAdapter.ViewHolder>(){
        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
            return ViewHolder(LayoutInflater.from(activity).inflate(R.layout.rv_child_dashboard, p0,false))
        }

        override fun onBindViewHolder(holder: ViewHolder, p1: Int) {
            holder.eventName.text = list[p1].name

            holder.eventName.setOnClickListener {
                val intent = Intent(activity, ItemActivity::class.java)
                intent.putExtra(INTENT_EVENT_ID,list[p1].id)
                intent.putExtra(INTENT_EVENT_NAME,list[p1].name)
                activity.startActivity(intent)
            }

            holder.menu.setOnClickListener{
                val popup = PopupMenu(activity, holder.menu)
                popup.inflate(R.menu.dashboard_child)
                popup.setOnMenuItemClickListener {

                    when(it.itemId){
                        R.id.menu_edit->{
                            activity.updateEvent(list[p1])
                        }
                        R.id.menu_delete->{
                            val dialog = AlertDialog.Builder(activity)
                            dialog.setTitle("Are you sure")
                            dialog.setMessage("Do you want to delete this event?")
                            dialog.setPositiveButton("Confirm delete") { _: DialogInterface, _: Int ->
                                activity.dbHandler.deleteEvent(list[p1].id)
                                activity.refreshList()
                            }
                            dialog.setNegativeButton("Cancel") { _: DialogInterface, _: Int ->
                            }
                            dialog.show()

                        }
                        R.id.menu_mark_as_completed->{
                            activity.dbHandler.updateEventItemCompletedStatus(list[p1].id, true)
                        }
                        R.id.menu_reset->{
                            activity.dbHandler.updateEventItemCompletedStatus(list[p1].id, false)
                        }
                    }
                    true
                }
                popup.show()
            }
        }

        override fun getItemCount(): Int {
            return list.size
        }

        class ViewHolder(v : View) : RecyclerView.ViewHolder(v){
            val eventName : TextView = v.findViewById(R.id.tv_todo_name)
            val menu : ImageView = v.findViewById(R.id.iv_menu)
        }


    }

}