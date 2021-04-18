@file:Suppress("LocalVariableName", "LocalVariableName", "LocalVariableName")

package com.example.clickhotelmanagementsystem.AccountsModule

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.clickhotelmanagementsystem.Database.Accounts.EditOwnProfile
import com.example.clickhotelmanagementsystem.Database.Accounts.UserViewModel
import com.example.clickhotelmanagementsystem.Manager.MainPageManager
import com.example.clickhotelmanagementsystem.R
import com.example.clickhotelmanagementsystem.databinding.FragmentAddUserAccountManagerBinding
import kotlinx.android.synthetic.main.fragment_add_user_account_manager.*
import kotlinx.android.synthetic.main.fragment_add_user_account_manager.view.*

class AddUserAccountManagerFragment : Fragment(R.layout.fragment_add_user_account_manager) {

    private lateinit var mUserViewModel: UserViewModel
    private lateinit var binding: FragmentAddUserAccountManagerBinding
    private lateinit var departmentValue: String
    private lateinit var positionValue: String
    private lateinit var departmentSpinner: Spinner
    private lateinit var positionSpinner: Spinner

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_add_user_account_manager, container, false)
        binding = FragmentAddUserAccountManagerBinding.bind(view)

        departmentSpinner = binding.departmentSpinner
        //val itemAtPosition = departmentSpinner.getItemAtPosition(0)
        /*        departmentSpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View, position: Int, id: Long) {
                //departmentValue = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }*/

        positionSpinner = binding.positionSpinner
        /*        positionSpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View, position: Int, id: Long) {
                //positionValue = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }*/

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.save_button.setOnClickListener {
            //Toast.makeText(requireContext(), "Something " + departmentSpinner.selectedItem.toString(), Toast.LENGTH_LONG).show()
            insertDataToDatabase()
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

    private fun insertDataToDatabase(){

        val firstName = addFirstName.text.toString()
        val lastName = addLastName.text.toString()
        val DOB = addDOB.text.toString()
        val IC = addIC.text.toString()
        val address = addAddress.text.toString()
        val email = addEmailAddress.text.toString()
        departmentValue = departmentSpinner.selectedItem.toString()
        /*        departmentSpinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                departmentValue = parent.getItemAtPosition(position).toString()
                Toast.makeText(requireContext(), "New Check" +parent.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show()
                //Code to insert selected string in database
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }*/
        positionValue = positionSpinner.selectedItem.toString()
        /*positionSpinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                positionValue = parent.getItemAtPosition(position).toString()
                //Code to insert selected string in database
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
*/
        val phoneNumber = addPhoneNumber.text.toString()
        val password = "abc"

        if(inputCheck(firstName,lastName,DOB,address,IC,email,departmentValue,positionValue,phoneNumber)){

            //Create user object
            val user = EditOwnProfile(0,firstName,lastName,DOB,address,Integer.parseInt(IC),email,departmentValue,positionValue,phoneNumber,password)

            //Add data to database
            mUserViewModel.addUser(user)

            //Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()

            //Navigate back
            findNavController().navigate(R.id.action_add_user_account_manager_to_list_users_manager)

        }else{
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG).show()
        }

    }

    //To check if input fields are filled
    private fun inputCheck(firstName: String,lastName: String,DOB: String,IC: String,address: String,
                           email: String,departmentValue: String,positionValue: String,phoneNumber: String): Boolean{
        return !(TextUtils.isEmpty(firstName) &&
                TextUtils.isEmpty(lastName) &&
                TextUtils.isEmpty(DOB) &&
                TextUtils.isEmpty(IC) &&
                TextUtils.isEmpty(address) &&
                TextUtils.isEmpty(email) &&
                TextUtils.isEmpty(departmentValue) &&
                TextUtils.isEmpty(positionValue) &&
                TextUtils.isEmpty(phoneNumber))
    }


}