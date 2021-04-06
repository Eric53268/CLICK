package com.example.tasks.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.clickhotelmanagementsystem.R
import com.example.tasks.model.Task
import kotlinx.android.synthetic.main.manager_custom_row.view.*

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var taskList = emptyList<Task>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.manager_custom_row, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = taskList[position]
        holder.itemView.id_txt.text = currentItem.id.toString()
        holder.itemView.task_txt.text = currentItem.taskDescription
        holder.itemView.name_txt.text = currentItem.staffName
        holder.itemView.date_txt.text = currentItem.date

        holder.itemView.managerRowLayout.setOnClickListener {
            val action = TaskListFragmentDirections.actionTaskListFragmentToUpdateTaskFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    fun setData(task: List<Task>) {
        this.taskList = task
        notifyDataSetChanged()
    }

}