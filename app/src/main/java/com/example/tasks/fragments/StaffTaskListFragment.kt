package com.example.tasks.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.clickhotelmanagementsystem.R
import com.example.tasks.viewmodel.TaskViewModel
import kotlinx.android.synthetic.main.fragment_staff_task_list.view.*

class StaffTaskListFragment : Fragment() {

    private lateinit var mTaskViewModel: TaskViewModel
    val adapter = StaffListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_staff_task_list, container, false)

        //Recycler view
        //val adapter = StaffListAdapter()
        val recyclerView = view.staff_task_list
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val name = "Alvin"

        //TaskViewModel
        mTaskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        mTaskViewModel.readParticularData(name).observe(viewLifecycleOwner, { list ->
            list.let {
                adapter.setData(it)
            }
        })

        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_home -> goToHome()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun goToHome() {
        findNavController().navigate(R.id.action_staffTaskListFragment_to_mainPageStaff)
    }

    /*override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)

        val search = menu.findItem(R.id.menu_search)
        val searchView = search?.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(this)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {
            searchDatabase(query)
        }
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        if (query != null) {
            searchDatabase(query)
        }
        return true
    }

    private fun searchDatabase(query: String) {
        val searchQuery = "%$query%"

        mTaskViewModel.searchDatabase(searchQuery).observe(this, { list ->
            list.let {
                adapter.setData(it)
            }
        })
    }*/
}