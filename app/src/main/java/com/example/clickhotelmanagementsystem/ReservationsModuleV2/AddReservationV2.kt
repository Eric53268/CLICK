package com.example.clickhotelmanagementsystem.ReservationsModuleV2

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.DatePicker
import android.widget.Spinner
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.clickhotelmanagementsystem.Database.Reservations.CustomerDetails
import com.example.clickhotelmanagementsystem.Database.Reservations.ReservationsViewModel
import com.example.clickhotelmanagementsystem.R
import com.example.clickhotelmanagementsystem.databinding.FragmentAddReservationV2Binding
import kotlinx.android.synthetic.main.fragment_add_reservation_v2.*
import java.util.*

class AddReservationV2 : Fragment() {

    private lateinit var mReservationsViewModel: ReservationsViewModel
    private lateinit var binding: FragmentAddReservationV2Binding
    private lateinit var roomTypeSpinner: Spinner
    private lateinit var breakfastCheckBox: CheckBox

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_reservation_v2, container, false)
        binding = FragmentAddReservationV2Binding.bind(view)

        roomTypeSpinner = binding.inputRoomType2
        breakfastCheckBox = binding.checkBoxBreakfastAdd
        //breakfastCheckBox.isChecked = false

        mReservationsViewModel = ViewModelProvider(this).get(ReservationsViewModel::class.java)

        val cIn = Calendar.getInstance()
        val cOut = Calendar.getInstance()
        val yearIn = cIn.get(Calendar.YEAR)
        val monthIn = cIn.get(Calendar.MONTH)
        val dateIn = cOut.get(Calendar.DAY_OF_MONTH)
        val yearOut = cOut.get(Calendar.YEAR)
        val monthOut = cOut.get(Calendar.MONTH)
        val dateOut = cOut.get(Calendar.DAY_OF_MONTH)

        binding.checkInDateButton.setOnClickListener{
            val dpdIn = DatePickerDialog(
                requireContext(),
                DatePickerDialog.OnDateSetListener { view: DatePicker?, inYear, inMonth, inDay ->
                    inputCheckInDate2.text =
                        inDay.toString() + "/" + inMonth.toString() + "/" + inYear.toString()
                },
                yearIn,
                monthIn,
                dateIn
            )
            dpdIn.show()}

        binding.checkOutDateButton.setOnClickListener{
            val dpdOut = DatePickerDialog(
                requireContext(),
                DatePickerDialog.OnDateSetListener { view: DatePicker?, outYear, outMonth, outDay ->
                    inputCheckOutDate2.text =
                        outDay.toString() + "/" + outMonth.toString() + "/" + outYear.toString()
                },
                yearOut,
                monthOut,
                dateOut
            )
            dpdOut.show()}

        binding.saveButton.setOnClickListener {
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase(){

        val reservationName = inputReservationNameField2.text.toString()
        val pax = inputPaxField2.text.toString()
        val breakfast = breakfastCheckBox.isChecked
        val checkInDate = inputCheckInDate2.text.toString()
        val checkOutDate = inputCheckOutDate2.text.toString()
        val roomTypeSpinner = inputRoomType2.selectedItem.toString()

        if(inputCheck(reservationName, pax, checkInDate, checkOutDate, roomTypeSpinner)){

            //Create reservation object
            val reservation = CustomerDetails(
                0,
                reservationName,
                Integer.parseInt(pax),
                breakfast,
                checkInDate,
                checkOutDate,
                roomTypeSpinner
            )

            mReservationsViewModel.addReservation(reservation)

            findNavController().navigate(R.id.action_addReservationV2_to_viewAllReservationsV2)

        }else{
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG).show()
        }

    }

    private fun inputCheck(
        reservationName: String, pax: String,
        checkInDate: String, checkOutDate: String,
        roomTypeSpinner: String
    ): Boolean{
        return !(TextUtils.isEmpty(reservationName) &&
                TextUtils.isEmpty(pax) &&
                TextUtils.isEmpty(checkInDate) &&
                TextUtils.isEmpty(checkOutDate) &&
                TextUtils.isEmpty(roomTypeSpinner))
    }

}