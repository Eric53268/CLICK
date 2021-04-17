package com.example.clickhotelmanagementsystem.AccountsModule

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.clickhotelmanagementsystem.Database.Accounts.EditOwnProfile
import com.example.clickhotelmanagementsystem.Database.Accounts.UserViewModel
import com.example.clickhotelmanagementsystem.R
import com.example.clickhotelmanagementsystem.databinding.FragmentUpdateUserAccountManagerBinding
import kotlinx.android.synthetic.main.fragment_update_user_account_manager.*
import kotlinx.android.synthetic.main.fragment_update_user_account_manager.view.*

class UpdateUserAccountManager : Fragment() {

    private val args by navArgs<UpdateUserAccountManagerArgs>()
    private lateinit var mUserViewModel: UserViewModel
    private lateinit var binding: FragmentUpdateUserAccountManagerBinding
    private lateinit var departmentValue: String
    private lateinit var positionValue: String
    private lateinit var departmentSpinner: Spinner
    private lateinit var positionSpinner: Spinner

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update_user_account_manager, container, false)

        binding = FragmentUpdateUserAccountManagerBinding.bind(view)
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.editID.setText(args.updateUser.uID.toString())
        view.editFirstName.setText(args.updateUser.firstName)
        view.editLastName.setText(args.updateUser.lastName)
        view.editDOB.setText(args.updateUser.DOB)
        view.editAddress.setText(args.updateUser.address)
        view.editIC.setText(args.updateUser.IC.toString())
        view.editEmailAddress.setText(args.updateUser.email)

        departmentSpinner = binding.departmentSpinnerUpdate
        val departmentArr = resources.getStringArray(R.array.departments).toList()
        val indexOfDepartment = departmentArr.indexOf(args.updateUser.department)
        departmentSpinner.setSelection(indexOfDepartment)

        positionSpinner = binding.positionSpinnerUpdate
        val positionArr = resources.getStringArray(R.array.positions).toList()
        val indexOfPosition = positionArr.indexOf(args.updateUser.position)
        positionSpinner.setSelection(indexOfPosition)

        view.editPhoneNumber.setText(args.updateUser.phoneNumber)

        view.edit_button.setOnClickListener {
            updateItem()

        }
        setHasOptionsMenu(true)

        return view

    }

    private fun updateItem(){

        val firstName = editFirstName.text.toString()
        val lastName = editLastName.text.toString()
        val DOB = editDOB.text.toString()
        val IC = Integer.parseInt(editIC.text.toString())
        val address = editAddress.text.toString()
        val emailAddress = editEmailAddress.text.toString()
        departmentValue = departmentSpinner.selectedItem.toString()
        positionValue = positionSpinner.selectedItem.toString()
        val phoneNumber = editPhoneNumber.text.toString()
        val password = "abc"

        if(inputCheck(firstName,lastName,DOB,IC,address,emailAddress,departmentValue, positionValue, phoneNumber)){

            //Create new user
            val updateUser = EditOwnProfile(args.updateUser.uID, firstName,lastName,DOB,address,IC,emailAddress,departmentValue,positionValue,phoneNumber,password)

            //Update current user
            mUserViewModel.updateUser(updateUser)
            Toast.makeText(requireContext(), "Updated successfully!", Toast.LENGTH_LONG).show()

            //Navigate back
            findNavController().navigate(R.id.action_updateUserAccountManager_to_list_users_manager)

        }else{
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(
        firstName: String,
        lastName: String,
        DOB: String,
        IC: Int,
        address: String,
        email: String,
        departmentValue: String,
        positionValue: String,
        phoneNumber: String
    ): Boolean{

        return !(TextUtils.isEmpty(firstName) &&
                TextUtils.isEmpty(lastName) &&
                TextUtils.isEmpty(DOB) &&
                TextUtils.isEmpty(IC.toString()) &&
                TextUtils.isEmpty(address) &&
                TextUtils.isEmpty(email) &&
                TextUtils.isEmpty(departmentValue) &&
                TextUtils.isEmpty(positionValue) &&
                TextUtils.isEmpty(phoneNumber))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_user_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.delete_user){
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_, _->
            mUserViewModel.deleteUser(args.updateUser)
            Toast.makeText(requireContext(), "Successfully removed: ${args.updateUser.firstName}",
                Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateUserAccountManager_to_list_users_manager)
        }
        builder.setNegativeButton("No"){_, _ ->}
        builder.setTitle("Delete ${args.updateUser.firstName}?")
        builder.setMessage("Are you sure you want to delete ${args.updateUser.firstName}?")
        builder.create().show()
    }

}