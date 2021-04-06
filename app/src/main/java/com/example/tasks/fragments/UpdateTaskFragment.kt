package com.example.tasks.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.tasks.R
import com.example.tasks.model.Task
import com.example.tasks.viewmodel.TaskViewModel
import kotlinx.android.synthetic.main.fragment_update_task.*
import kotlinx.android.synthetic.main.fragment_update_task.view.*

class UpdateTaskFragment : Fragment() {

    private val args by navArgs<UpdateTaskFragmentArgs>()

    private lateinit var mTaskViewModel: TaskViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_update_task, container, false)

        mTaskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        view.updateAddTask.setText(args.currentTask.taskDescription)
        view.updateAddName.setText(args.currentTask.staffName)
        view.updateAddDate.setText(args.currentTask.date)

        view.confirm_update.setOnClickListener {
            updateItem()
        }

        view.cancel_add.setOnClickListener {
            goToPreviousPage()
        }

        //Add menu
        setHasOptionsMenu(true)

        return view
    }

    private fun goToPreviousPage() {
        findNavController().navigate(R.id.action_updateTaskFragment_to_taskListFragment)
    }

    private fun updateItem() {
        val taskDescription = updateAddTask.text.toString()
        val name = updateAddName.text.toString()
        val assignedDate = updateAddDate.text.toString()

        if (inputCheck(taskDescription, name, assignedDate)) {
            //Create Task Object
            val updatedTask = Task(args.currentTask.id, taskDescription, name, assignedDate)
            //Update Current Task
            mTaskViewModel.updateTask(updatedTask)
            Toast.makeText(requireContext(), "Updated successfully.", Toast.LENGTH_LONG).show()
            //Navigate Back
            findNavController().navigate(R.id.action_updateTaskFragment_to_taskListFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(taskDescription: String, name: String, assignedDate: String): Boolean {
        return !(TextUtils.isEmpty(taskDescription) || TextUtils.isEmpty(name) || TextUtils.isEmpty(assignedDate))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteTask()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteTask() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mTaskViewModel.deleteTask(args.currentTask)
            Toast.makeText(
                requireContext(),
                "Successfully removed: ${args.currentTask.taskDescription}", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateTaskFragment_to_taskListFragment)
        }
        builder.setNegativeButton("No") { _, _ ->}
        builder.setTitle("Delete ${args.currentTask.taskDescription}?")
        builder.setMessage("Are you sure you want to delete ${args.currentTask.taskDescription}?")
        builder.create().show()
    }

}