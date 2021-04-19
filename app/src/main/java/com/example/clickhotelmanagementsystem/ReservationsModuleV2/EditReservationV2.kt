package com.example.clickhotelmanagementsystem.ReservationsModuleV2

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.CheckBox
import android.widget.DatePicker
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.clickhotelmanagementsystem.Database.Reservations.CustomerDetails
import com.example.clickhotelmanagementsystem.Database.Reservations.ReservationsViewModel
import com.example.clickhotelmanagementsystem.Manager.MainPageManager
import com.example.clickhotelmanagementsystem.R
import com.example.clickhotelmanagementsystem.databinding.FragmentEditReservationV2Binding
import kotlinx.android.synthetic.main.fragment_edit_reservation_v2.*
import kotlinx.android.synthetic.main.fragment_edit_reservation_v2.view.*
import java.util.*

class EditReservationV2 : Fragment() {

    private lateinit var binding: FragmentEditReservationV2Binding
    private lateinit var mReservationsViewModel: ReservationsViewModel
    private val argsReservationEdit by navArgs<EditReservationV2Args>()
    private lateinit var inputRoomTypeSpinner2: Spinner
    private lateinit var inputRoomTypeSelected: String
    private lateinit var checkboxBreakfastEdit: CheckBox


    private val cIn: Calendar = Calendar.getInstance()
    private val cOut = Calendar.getInstance()
    private val yearIn = cIn.get(Calendar.YEAR)
    private val monthIn = cIn.get(Calendar.MONTH)
    private val dateIn = cOut.get(Calendar.DAY_OF_MONTH)
    private val yearOut = cOut.get(Calendar.YEAR)
    private val monthOut = cOut.get(Calendar.MONTH)
    private val dateOut = cOut.get(Calendar.DAY_OF_MONTH)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_edit_reservation_v2, container, false)

        binding = FragmentEditReservationV2Binding.bind(view)
        mReservationsViewModel = ViewModelProvider(this).get(ReservationsViewModel::class.java)
        checkboxBreakfastEdit = view.checkBoxBreakfastEdit

        inputRoomTypeSpinner2 = binding.inputRoomType2Edit
        val roomTypeArr = resources.getStringArray(R.array.roomType).toList()
        val indexOfRoom = roomTypeArr.indexOf(argsReservationEdit.editReservation.roomType)
        inputRoomTypeSpinner2.setSelection(indexOfRoom)
        inputRoomTypeSelected = inputRoomTypeSpinner2.selectedItem.toString()
        //Toast.makeText(requireContext(), inputRoomTypeSelected, Toast.LENGTH_LONG).show()

        view.inputReservationNameField2Edit.setText(argsReservationEdit.editReservation.reservationName)

        view.inputPax2Edit.placeholderText = argsReservationEdit.editReservation.pax.toString()

        view.inputCheckInDate2Edit.setText(argsReservationEdit.editReservation.checkInDate)
        view.inputCheckOutDate2Edit.setText(argsReservationEdit.editReservation.checkOutDate)

        view.checkInDateButtonEdit.setOnClickListener{
            val dpdInEdit = DatePickerDialog(
                requireContext(),R.style.DialogTheme,DatePickerDialog.OnDateSetListener
                { view: DatePicker?, inYear, inMonth, inDay ->
                    inputCheckInDate2Edit.setText(inDay.toString() + "/" + inMonth.toString() + "/" + inYear.toString())

                },
                yearIn,
                monthIn,
                dateIn
            )
            dpdInEdit.show()
            dpdInEdit.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK)
            dpdInEdit.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK)
        }

        view.checkOutDateButtonEdit.setOnClickListener{
            val dpdOutEdit = DatePickerDialog(
                requireContext(),R.style.DialogTheme, DatePickerDialog.OnDateSetListener
                { view: DatePicker?, outYear, outMonth, outDay ->
                    inputCheckOutDate2Edit.setText(outDay.toString() + "/" + outMonth.toString() + "/" + outYear.toString())

                },
                yearOut,
                monthOut,
                dateOut
            )
            dpdOutEdit.show()
            dpdOutEdit.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK)
            dpdOutEdit.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK)
        }

        checkboxBreakfastEdit.isChecked = argsReservationEdit.editReservation.breakfast

        view.save_edit_button.setOnClickListener{
            updateReservation()
        }
        setHasOptionsMenu(true)

        return view
    }

    /*fun wannacry(): Int {
        if(){
            return Integer.parseInt(inputPax2Edit.placeholderText.toString())
        }



    }*/




    private fun updateReservation(){

        inputRoomTypeSelected= inputRoomTypeSpinner2.selectedItem.toString()
        val reservationName2 = inputReservationName2Edit.editText?.text.toString()
        val breakfast = checkboxBreakfastEdit.isChecked
        val checkInDate2 = inputCheckInDate2Edit.text.toString()
        val checkOutDate2 = inputCheckOutDate2Edit.text.toString()
        var pax = 0

        if(inputPax2Edit.editText?.text.toString().isEmpty()){
            pax = Integer.parseInt(inputPax2Edit.placeholderText.toString())
        }
        else{
            pax = Integer.parseInt(inputPax2Edit.editText!!.text.toString())
        }



        if(inputCheck(inputRoomTypeSelected, reservationName2, checkInDate2, checkOutDate2)){


            //Create new user
            val updateReservation = CustomerDetails(argsReservationEdit.editReservation.roomID,
                reservationName2,pax,breakfast,checkInDate2,checkOutDate2,inputRoomTypeSelected)

            //Update current user
            mReservationsViewModel.updateReservation(updateReservation)

            //Navigate back
            findNavController().navigate(R.id.action_editReservationV2_to_viewAllReservationsV2)

        }else{
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(
        roomType: String,
        reservationName: String,
        //pax: Int,
        checkInDate: String,
        checkOutDate: String
    ): Boolean{

        return !(TextUtils.isEmpty(roomType) &&
                TextUtils.isEmpty(reservationName) &&
                //TextUtils.isEmpty(pax.toString()) &&
                TextUtils.isEmpty(checkInDate) &&
                TextUtils.isEmpty(checkOutDate))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_reservation_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.delete_user){
            deleteReservation()
        }else if(item.itemId == R.id.home_icon){
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

    private fun deleteReservation(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_, _->
            mReservationsViewModel.deleteReservation(argsReservationEdit.editReservation)
            Toast.makeText(requireContext(), "Successfully removed: ${argsReservationEdit.editReservation.reservationName}",
                Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_editReservationV2_to_viewAllReservationsV2)
        }
        builder.setNegativeButton("No"){_, _ ->}
        builder.setTitle("Delete ${argsReservationEdit.editReservation.reservationName}?")
        builder.setMessage("Are you sure you want to delete ${argsReservationEdit.editReservation.reservationName}?")
        builder.create().show()
    }

}
