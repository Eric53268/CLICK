package com.example.clickhotelmanagementsystem.ReservationsModule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.clickhotelmanagementsystem.Database.Reservations.ReservationsViewModel
import com.example.clickhotelmanagementsystem.R
import com.example.clickhotelmanagementsystem.databinding.FragmentViewReservationsStandardRoomBinding
import kotlinx.android.synthetic.main.fragment_view_reservations_standard_room.view.*

class ViewReservationsStandardRoom : Fragment() {

    private lateinit var binding: FragmentViewReservationsStandardRoomBinding
    private lateinit var mReservationsViewModel: ReservationsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_view_reservations_standard_room, container, false)
        binding = FragmentViewReservationsStandardRoomBinding.bind(view)

        //Recyclerview
        val adapter = ListAdapterReservations()
        val recyclerView = view.view_reservations
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //ReservationsViewModel
        mReservationsViewModel = ViewModelProvider(this).get(ReservationsViewModel::class.java)
        mReservationsViewModel.readAllData.observe(viewLifecycleOwner, {reservation -> adapter.setData(reservation)})

        binding.addButton.setOnClickListener {
            findNavController().navigate(R.id.action_viewReservations2_to_addReservation)
        }

        return view

    }

}