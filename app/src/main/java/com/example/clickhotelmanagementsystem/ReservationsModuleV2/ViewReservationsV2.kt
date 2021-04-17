package com.example.clickhotelmanagementsystem.ReservationsModuleV2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.clickhotelmanagementsystem.R
import com.example.clickhotelmanagementsystem.databinding.FragmentViewReservationsV2Binding

class ViewReservationsV2 : Fragment() {

    private lateinit var binding: FragmentViewReservationsV2Binding
    private val argsReservationView by navArgs<ViewReservationsV2Args>()
    lateinit var isCheckedBoxBreakfast: CheckBox

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_view_reservations_v2, container, false)
        binding = FragmentViewReservationsV2Binding.bind(view)

        isCheckedBoxBreakfast = binding.checkBoxBreakfastView

        binding.viewRoomType2View.text = argsReservationView.currentReservation.roomType.toString()
        binding.viewReservationName2View.text = argsReservationView.currentReservation.reservationName.toString()
        binding.viewPax2View.text = argsReservationView.currentReservation.pax.toString()
        binding.viewCheckInDate2View.text = argsReservationView.currentReservation.checkInDate.toString()
        binding.viewCheckOutDate2View.text = argsReservationView.currentReservation.checkOutDate.toString()
        isCheckedBoxBreakfast.isChecked = argsReservationView.currentReservation.breakfast

        binding.viewEditButton.setOnClickListener {
            val action = ViewReservationsV2Directions.actionViewReservationsV2ToEditReservationV22(argsReservationView.currentReservation)
            findNavController().navigate(action)
        }

        return view
    }

}