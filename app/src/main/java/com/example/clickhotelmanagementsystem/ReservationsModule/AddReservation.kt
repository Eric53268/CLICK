package com.example.clickhotelmanagementsystem.ReservationsModule

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.clickhotelmanagementsystem.databinding.FragmentAddReservationBinding

class AddReservation : Fragment() {

    private var binding: FragmentAddReservationBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding = FragmentAddReservationBinding.bind(view)
    }

}