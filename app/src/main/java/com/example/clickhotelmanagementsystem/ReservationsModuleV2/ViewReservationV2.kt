package com.example.clickhotelmanagementsystem.ReservationsModuleV2

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.CheckBox
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.clickhotelmanagementsystem.Manager.MainPageManager
import com.example.clickhotelmanagementsystem.R
import com.example.clickhotelmanagementsystem.databinding.FragmentViewReservationsV2Binding

class ViewReservationV2 : Fragment() {

    private lateinit var binding: FragmentViewReservationsV2Binding
    private val argsReservationView by navArgs<ViewReservationV2Args>()
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
            val action = ViewReservationV2Directions.actionViewReservationsV2ToEditReservationV22(argsReservationView.currentReservation)
            findNavController().navigate(action)
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