package com.example.clickhotelmanagementsystem.ReservationsModule

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.clickhotelmanagementsystem.Database.Reservations.CustomerDetails
import com.example.clickhotelmanagementsystem.Database.Reservations.ReservationsViewModel
import com.example.clickhotelmanagementsystem.R
import com.example.clickhotelmanagementsystem.databinding.FragmentAddReservationBinding
import kotlinx.android.synthetic.main.fragment_add_reservation.*
import kotlinx.android.synthetic.main.fragment_add_reservation.view.*
import java.util.*

class AddReservation : Fragment(R.layout.fragment_add_reservation) {

    private lateinit var checkInDate: String
    private lateinit var checkOutDate: String

    private lateinit var mReservationsViewModel: ReservationsViewModel
    private lateinit var binding: FragmentAddReservationBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_add_reservation, container, false)
        binding = FragmentAddReservationBinding.bind(view)

        mReservationsViewModel = ViewModelProvider(this).get(ReservationsViewModel::class.java)

        val cIn = Calendar.getInstance()
        val cOut = Calendar.getInstance()
        val yearIn = cIn.get(Calendar.YEAR)
        val monthIn = cIn.get(Calendar.MONTH)
        val dateIn = cOut.get(Calendar.DAY_OF_MONTH)
        val yearOut = cOut.get(Calendar.YEAR)
        val monthOut = cOut.get(Calendar.MONTH)
        val dateOut = cOut.get(Calendar.DAY_OF_MONTH)

        view.checkInDateButton.setOnClickListener{
            val dpdIn = DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener{view: DatePicker?,inYear,inMonth,inDay ->
            inputCheckInDate.text = inDay.toString()+"/"+inMonth.toString()+"/"+inYear.toString()},yearIn,monthIn,dateIn)
            dpdIn.show()}

        view.checkOutDateButton.setOnClickListener{
            val dpdOut = DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener{view: DatePicker?,outYear,outMonth,outDay ->
            inputCheckOutDate.text = outDay.toString()+"/"+outMonth.toString()+"/"+outYear.toString()},yearOut,monthOut,dateOut)
            dpdOut.show()}

        view.addButton.setOnClickListener {
            Toast.makeText(requireContext(), "Check", Toast.LENGTH_LONG).show()
            insertDataToDatabase()
        }

        return view
    }

    /*private fun getDateTimeCalendar(){

        val checkInCal = Calendar.getInstance()
        checkInDay = checkInCal.get(Calendar.DAY_OF_MONTH)
        checkInMonth = checkInCal.get(Calendar.MONTH)
        checkInYear = checkInCal.get(Calendar.YEAR)

        val checkOutCal = Calendar.getInstance()
        checkOutDay = checkOutCal.get(Calendar.DAY_OF_MONTH)
        checkOutMonth = checkOutCal.get(Calendar.MONTH)
        checkOutYear = checkOutCal.get(Calendar.YEAR)

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

            // Display Selected date in textbox
            inputCheckInDate.setText("" + dayOfMonth + " " + MONTHS[monthOfYear] + ", " + year)

        }, year, month, day)

        dpd.show()

    }*/

    private fun insertDataToDatabase(){

        val reservationName = inputReservationNameField.text.toString()
        val pax = inputPaxField.text.toString()
        val breakfast = checkBoxBreakfast.isChecked
        val checkInDate = inputCheckInDate.text.toString()
        val checkOutDate = inputCheckOutDate.text.toString()

        if(inputCheck(reservationName, pax, checkInDate, checkOutDate)){

            //Create reservation object
            val reservation = CustomerDetails(0, reservationName, Integer.parseInt(pax), breakfast, checkInDate, checkOutDate)

            mReservationsViewModel.addReservation(reservation)

            findNavController().navigate(R.id.action_addReservationStandardRoom_to_view_reservations_standard_room)

        }else{
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG).show()
        }

    }

    private fun inputCheck(reservationName: String, pax: String,
                           checkInDate: String, checkOutDate: String): Boolean{
        return !(TextUtils.isEmpty(reservationName) &&
                TextUtils.isEmpty(pax) &&
                TextUtils.isEmpty(checkInDate) &&
                TextUtils.isEmpty(checkOutDate))
    }

    /*override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {

        checkInSavedDay = dayOfMonth
        checkInSavedMonth = month
        checkInSavedYear = year
        getDateTimeCalendar()
        checkInDate = "$checkInSavedDay-$checkInSavedMonth-$checkInSavedYear"
        inputCheckInDate.text = checkInDate

        checkOutSavedDay = dayOfMonth
        checkOutSavedMonth = month
        checkOutSavedYear = year
        getDateTimeCalendar()
        checkOutDate = "$checkOutSavedDay-$checkOutSavedMonth-$checkOutSavedYear"
        inputCheckOutDate.text = checkOutDate
    }*/

}