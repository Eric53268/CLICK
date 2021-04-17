package com.example.tasks.fragments

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.clickhotelmanagementsystem.R
import com.example.tasks.model.Task
import kotlinx.android.synthetic.main.staff_custom_row.view.*

class StaffListAdapter: RecyclerView.Adapter<StaffListAdapter.MyViewHolder>() {

    private var taskList = emptyList<Task>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.staff_custom_row, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = taskList[position]
        //holder.itemView.id_staff_txt.text = currentItem.id.toString()
        holder.itemView.task_staff_txt.text = currentItem.taskDescription
        holder.itemView.staff_event_txt.text = currentItem.event
        if (holder.itemView.staff_event_txt.text != "-")
            holder.itemView.setBackgroundColor(Color.rgb(137,207,240))
            //holder.itemView.staff_event_txt.setTextColor(0x00000000)
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    fun setData(task: List<Task>) {
        this.taskList = task
        notifyDataSetChanged()
    }
}