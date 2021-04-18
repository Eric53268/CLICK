package com.example.clickhotelmanagementsystem.ReservationsModuleV2

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.clickhotelmanagementsystem.Database.Reservations.ReservationsViewModel
import com.example.clickhotelmanagementsystem.Manager.MainPageManager
import com.example.clickhotelmanagementsystem.R
import com.example.clickhotelmanagementsystem.databinding.FragmentViewAllReservationsV2Binding
import kotlinx.android.synthetic.main.fragment_view_all_reservations_v2.view.*

class ViewAllReservationsV2 : Fragment() {

    private lateinit var binding: FragmentViewAllReservationsV2Binding
    private lateinit var mReservationsViewModel: ReservationsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_view_all_reservations_v2, container, false)
        binding = FragmentViewAllReservationsV2Binding.bind(view)

        //Recyclerview
        val adapter = ListAdapterReservationsV2()
        val recyclerView = view.view_reservations_v2
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //UserViewModel
        mReservationsViewModel = ViewModelProvider( this).get(ReservationsViewModel::class.java)
        mReservationsViewModel.readAllData.observe(viewLifecycleOwner, Observer { reservation -> adapter.setData(reservation)})

        binding.addButton.setOnClickListener {
            findNavController().navigate(R.id.action_viewAllReservationsV2_to_addReservationV2)
        }
        setHasOptionsMenu(true)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.back_home_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.home_icon){
            backHome()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun backHome(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_, _->
            val intent = Intent(requireContext(), MainPageManager::class.java)
            startActivity(intent)
        }
        builder.setNegativeButton("No"){_, _ ->}
        builder.setMessage("Are you sure you want to go back to the main page?")
        builder.create().show()
    }

}