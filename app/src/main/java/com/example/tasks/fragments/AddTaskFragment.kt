package com.example.tasks.fragments

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.tasks.R
import com.example.tasks.model.Task
import com.example.tasks.viewmodel.TaskViewModel
import kotlinx.android.synthetic.main.fragment_add_task.*
import kotlinx.android.synthetic.main.fragment_add_task.view.*

@Suppress("NAME_SHADOWING")
class AddTaskFragment : Fragment() {

    private lateinit var mTaskViewModel: TaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_task, container, false)

        mTaskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        view.confirm_add.setOnClickListener {
            insertDataToDatabase()
        }

        view.cancel_add.setOnClickListener {
            goToPreviousFragment()
        }

        return view
    }

    private fun insertDataToDatabase() {
        val taskDescription = addTask.text.toString()
        val name = addName.text.toString()
        val assignedDate = addDate.text.toString()

        if (inputCheck(taskDescription, name, assignedDate)) {
            //Create Task Object
            val task = Task(0, taskDescription, name, assignedDate)
            //Add Data to Database
            mTaskViewModel.addTask(task)
            Toast.makeText(requireContext(), "Successfully added.", Toast.LENGTH_LONG).show()
            //Navigate Back
            findNavController().navigate(R.id.action_addTaskFragment_to_taskListFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(taskDescription: String, name: String, assignedDate: String): Boolean {
        return !(TextUtils.isEmpty(taskDescription) || TextUtils.isEmpty(name) || TextUtils.isEmpty(assignedDate))
    }

    private fun goToPreviousFragment() {
        findNavController().navigate(R.id.action_addTaskFragment_to_taskListFragment)
    }
}