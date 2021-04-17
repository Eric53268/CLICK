package com.example.clickhotelmanagementsystem.ReservationsModuleV2

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.clickhotelmanagementsystem.Database.Reservations.CustomerDetails
import com.example.clickhotelmanagementsystem.Database.Reservations.ReservationsViewModel
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
    lateinit var checkboxBreakfastEdit: CheckBox
    private lateinit var checkIn: CalendarView
    private lateinit var checkOut: CalendarView

    val cIn = Calendar.getInstance()
    val cOut = Calendar.getInstance()
    val yearIn = cIn.get(Calendar.YEAR)
    val monthIn = cIn.get(Calendar.MONTH)
    val dateIn = cOut.get(Calendar.DAY_OF_MONTH)
    val yearOut = cOut.get(Calendar.YEAR)
    val monthOut = cOut.get(Calendar.MONTH)
    val dateOut = cOut.get(Calendar.DAY_OF_MONTH)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_edit_reservation_v2, container, false)
        binding = FragmentEditReservationV2Binding.bind(view)
        mReservationsViewModel = ViewModelProvider(this).get(ReservationsViewModel::class.java)

        checkboxBreakfastEdit = binding.checkBoxBreakfastEdit

        inputRoomTypeSpinner2 = binding.inputRoomType2Edit
        val roomTypeArr = resources.getStringArray(R.array.roomType).toList()
        val indexOfRoom = roomTypeArr.indexOf(argsReservationEdit.editReservation.roomType)
        inputRoomTypeSpinner2.setSelection(indexOfRoom)

        binding.inputReservationName2Edit.placeholderText = argsReservationEdit.editReservation.reservationName
        binding.inputPax2Edit.placeholderText = argsReservationEdit.editReservation.pax.toString()

        binding.checkInDateButtonEdit.setOnClickListener{
            val dpdInEdit = DatePickerDialog(
                requireContext(),
                DatePickerDialog.OnDateSetListener { view: DatePicker?, inYear, inMonth, inDay ->
                    inputCheckInDate2Edit.text =
                        inDay.toString() + "/" + inMonth.toString() + "/" + inYear.toString()
                },
                yearIn,
                monthIn,
                dateIn
            )
            dpdInEdit.show()}

        binding.checkOutDateButtonEdit.setOnClickListener{
            val dpdOutEdit = DatePickerDialog(
                requireContext(),
                DatePickerDialog.OnDateSetListener { view: DatePicker?, outYear, outMonth, outDay ->
                    inputCheckOutDate2Edit.text =
                        outDay.toString() + "/" + outMonth.toString() + "/" + outYear.toString()
                },
                yearOut,
                monthOut,
                dateOut
            )
            dpdOutEdit.show()}
        checkboxBreakfastEdit.isChecked = argsReservationEdit.editReservation.breakfast

        Toast.makeText(requireContext(), "oiiiiiiiiiiiiiii!", Toast.LENGTH_LONG).show()
        binding.saveEditButton.setOnClickListener{
            updateReservation()
        }
        setHasOptionsMenu(true)

        return view
    }

    private fun updateReservation(){

        val roomType2 = inputRoomTypeSpinner2.inputRoomType2Edit.selectedItem.toString()
        val reservationName2 = binding.inputReservationName2Edit.placeholderText.toString()
        val pax = binding.inputPax2Edit.placeholderText.toString()
        val breakfast = checkboxBreakfastEdit.isChecked
        val checkInDate2 = binding.inputCheckInDate2Edit.text.toString()
        val checkOutDate2 = binding.inputCheckOutDate2Edit.text.toString()
        binding.checkInDateButtonEdit.setOnClickListener{
            val dpdInEdit = DatePickerDialog(
                requireContext(),
                DatePickerDialog.OnDateSetListener { view: DatePicker?, inYear, inMonth, inDay ->
                    inputCheckInDate2Edit.text =
                        inDay.toString() + "/" + inMonth.toString() + "/" + inYear.toString()
                },
                yearIn,
                monthIn,
                dateIn
            )
            dpdInEdit.show()}

        binding.checkOutDateButtonEdit.setOnClickListener{
            val dpdOutEdit = DatePickerDialog(
                requireContext(),
                DatePickerDialog.OnDateSetListener { view: DatePicker?, outYear, outMonth, outDay ->
                    inputCheckOutDate2Edit.text =
                        outDay.toString() + "/" + outMonth.toString() + "/" + outYear.toString()
                },
                yearOut,
                monthOut,
                dateOut
            )
            dpdOutEdit.show()}

        if(inputCheck(roomType2, reservationName2, pax, checkInDate2, checkOutDate2)){

            //Create new user
            val updateReservation = CustomerDetails(argsReservationEdit.editReservation.roomID,
                reservationName2,Integer.parseInt(pax),breakfast,checkInDate2,checkOutDate2,roomType2)

            //Update current user
            mReservationsViewModel.updateReservation(updateReservation)
            Toast.makeText(requireContext(), "Updated successfully!", Toast.LENGTH_LONG).show()

            //Navigate back
            Toast.makeText(requireContext(), "Hello!", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_editReservationV2_to_viewReservationsV2)

        }else{
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(
        roomType: String?,
        reservationName: String?,
        pax: String?,
        checkInDate: String?,
        checkOutDate: String?
    ): Boolean{

        return !(TextUtils.isEmpty(roomType) &&
                TextUtils.isEmpty(reservationName) &&
                TextUtils.isEmpty(pax.toString()) &&
                TextUtils.isEmpty(checkInDate) &&
                TextUtils.isEmpty(checkOutDate))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_user_reservation_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.delete_user){
            deleteReservation()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteReservation(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_, _->
            mReservationsViewModel.deleteReservation(argsReservationEdit.editReservation)
            Toast.makeText(requireContext(), "Successfully removed: ${argsReservationEdit.editReservation.reservationName}",
                Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_editReservationV2_to_viewReservationsV2)
        }
        builder.setNegativeButton("No"){_, _ ->}
        builder.setTitle("Delete ${argsReservationEdit.editReservation.reservationName}?")
        builder.setMessage("Are you sure you want to delete ${argsReservationEdit.editReservation.reservationName}?")
        builder.create().show()
    }

}