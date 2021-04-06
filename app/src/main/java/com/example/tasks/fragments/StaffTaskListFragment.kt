package com.example.tasks.fragments

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tasks.R
import com.example.tasks.viewmodel.TaskViewModel
import kotlinx.android.synthetic.main.fragment_staff_task_list.view.*

class StaffTaskListFragment : Fragment(), SearchView.OnQueryTextListener {

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

        //TaskViewModel
        mTaskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        mTaskViewModel.readAllData.observe(viewLifecycleOwner, Observer { task ->
            adapter.setData(task)
        })

        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
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
    }
}