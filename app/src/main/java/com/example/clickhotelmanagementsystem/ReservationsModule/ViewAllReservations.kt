package com.example.clickhotelmanagementsystem.ReservationsModule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.clickhotelmanagementsystem.R
import com.example.clickhotelmanagementsystem.databinding.FragmentViewAllReservationsBinding

class ViewAllReservations : Fragment() {

    private lateinit var binding: FragmentViewAllReservationsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_all_reservations, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentViewAllReservationsBinding.bind(view)

        binding.standardRoomDetail.setOnClickListener {
            findNavController().navigate(R.id.action_view_all_reservations_to_view_standard_room)
        }

        binding.deluxeRoomDetail.setOnClickListener {
            findNavController().navigate(R.id.action_view_all_reservations_to_view_deluxe_room)
        }

        binding.premiumRoomDetail.setOnClickListener {
            findNavController().navigate(R.id.action_view_all_reservations_to_view_premium_room)
        }

        binding.lexisSuiteDetail.setOnClickListener {
            findNavController().navigate(R.id.action_view_all_reservations_to_view_lexis_suite)
        }

/*        view.addButton.setOnClickListener{
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }*/
    }

}