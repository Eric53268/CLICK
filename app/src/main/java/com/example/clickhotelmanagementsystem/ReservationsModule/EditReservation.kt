package com.example.clickhotelmanagementsystem.ReservationsModule

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.DatePicker
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.clickhotelmanagementsystem.Database.Reservations.CustomerDetails
import com.example.clickhotelmanagementsystem.Database.Reservations.ReservationsViewModel
import com.example.clickhotelmanagementsystem.R
import com.example.clickhotelmanagementsystem.databinding.FragmentEditReservationBinding
import kotlinx.android.synthetic.main.fragment_add_reservation.*
import kotlinx.android.synthetic.main.fragment_edit_reservation.view.*
import java.util.*

class EditReservation : Fragment() {

    private lateinit var binding: FragmentEditReservationBinding
    private lateinit var mReservationsViewModel: ReservationsViewModel
    private val argsReservation by navArgs<EditReservationArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_edit_reservation, container, false)
        binding = FragmentEditReservationBinding.bind(view)
        mReservationsViewModel = ViewModelProvider(this).get(ReservationsViewModel::class.java)

        view.inputRoomID.text = argsReservation.currentReservation.roomID.toString()
        view.inputReservationName.placeholderText = argsReservation.currentReservation.reservationName.toString()
        view.inputPax.placeholderText = argsReservation.currentReservation.pax.toString()
        //view.inputPax.setText(argsReservation.currentReservation.pax.toString())
        view.inputCheckInDate.text = argsReservation.currentReservation.checkInDate.toString()
        view.inputCheckOutDate.text = argsReservation.currentReservation.checkOutDate.toString()

        val cIn = Calendar.getInstance()
        val cOut = Calendar.getInstance()
        val yearIn = cIn.get(Calendar.YEAR)
        val monthIn = cIn.get(Calendar.MONTH)
        val dateIn = cOut.get(Calendar.DAY_OF_MONTH)
        val yearOut = cOut.get(Calendar.YEAR)
        val monthOut = cOut.get(Calendar.MONTH)
        val dateOut = cOut.get(Calendar.DAY_OF_MONTH)

        view.checkInDateButton.setOnClickListener{
            val dpdIn = DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener{ view: DatePicker?, inYear, inMonth, inDay ->
                inputCheckInDate.text = inDay.toString()+"/"+inMonth.toString()+"/"+inYear.toString()},yearIn,monthIn,dateIn)
            dpdIn.show()}

        view.checkOutDateButton.setOnClickListener{
            val dpdOut = DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener{ view: DatePicker?, outYear, outMonth, outDay ->
                inputCheckOutDate.text = outDay.toString()+"/"+outMonth.toString()+"/"+outYear.toString()},yearOut,monthOut,dateOut)
            dpdOut.show()}

        view.saveReservationButton.setOnClickListener {
            updateItem()

        }
        setHasOptionsMenu(true)

        return view

    }

    private fun updateItem(){

        val reservationName = inputReservationNameField.text.toString()
        val pax = Integer.parseInt(inputPaxField.text.toString())
        val breakfast = checkBoxBreakfast.isChecked
        val checkInDate = inputCheckInDate.text.toString()
        val checkOutDate = inputCheckOutDate.text.toString()

        if(inputCheck(reservationName, pax, checkInDate, checkOutDate)){

            //Create reservation object
            val updateReservation = CustomerDetails(argsReservation.currentReservation.roomID, reservationName, pax, breakfast, checkInDate, checkOutDate)

            mReservationsViewModel.updateReservation(updateReservation)
            Toast.makeText(requireContext(), "Updated successfully!", Toast.LENGTH_LONG).show()

            findNavController().navigate(R.id.action_editReservationStandardRoom_to_view_reservations_standard_room)

        }else{
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG).show()
        }

    }

    private fun inputCheck(reservationName: String, pax: Int,
                           checkInDate: String, checkOutDate: String): Boolean{
        return !(TextUtils.isEmpty(reservationName) &&
                TextUtils.isEmpty(pax.toString()) &&
                TextUtils.isEmpty(checkInDate) &&
                TextUtils.isEmpty(checkOutDate))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_reservation_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.delete_reservation){
            deleteReservation()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteReservation(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_, _->
            mReservationsViewModel.deleteReservation(argsReservation.currentReservation)
            Toast.makeText(requireContext(), "Successfully removed: ${argsReservation.currentReservation.reservationName}",
                Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_editReservationStandardRoom_to_view_reservations_standard_room)
        }
        builder.setNegativeButton("No"){_, _ ->}
        builder.setTitle("Delete ${argsReservation.currentReservation.reservationName}?")
        builder.setMessage("Are you sure you want to delete ${argsReservation.currentReservation.reservationName}?")
        builder.create().show()
    }

}