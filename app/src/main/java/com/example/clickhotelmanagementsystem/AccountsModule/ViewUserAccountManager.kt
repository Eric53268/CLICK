package com.example.clickhotelmanagementsystem.AccountsModule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.clickhotelmanagementsystem.R
import com.example.clickhotelmanagementsystem.databinding.FragmentViewUserAccountManagerBinding
import kotlinx.android.synthetic.main.fragment_view_user_account_manager.view.*

class ViewUserAccountManager : Fragment() {

    private val argsUser by navArgs<ViewUserAccountManagerArgs>()
    //private lateinit var mUserViewModel: UserViewModel
    private lateinit var binding: FragmentViewUserAccountManagerBinding
    /*private lateinit var departmentValue: String
    private lateinit var positionValue: String
    private lateinit var departmentSpinner: Spinner*/

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_view_user_account_manager, container, false)
        binding = FragmentViewUserAccountManagerBinding.bind(view)
        //mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.editID.text = argsUser.currentUser.uID.toString()
        view.editFirstName.text = argsUser.currentUser.firstName
        view.editLastName.text = argsUser.currentUser.lastName
        view.editDOB.text = argsUser.currentUser.DOB
        view.editAddress.text = argsUser.currentUser.address
        view.editIC.text = argsUser.currentUser.IC.toString()
        view.editEmailAddress.text = argsUser.currentUser.email
        view.editDepartment.text = argsUser.currentUser.department
        view.editPosition.text = argsUser.currentUser.position
        view.editPhoneNumber.text = argsUser.currentUser.phoneNumber

        view.edit_button.setOnClickListener {
            //val action = ListUsersManagerFragmentDirections.actionListUsersManagerToViewUserAccountManager(argsUser.currentUser)
            val action = ViewUserAccountManagerDirections.actionViewUserAccountManagerToUpdateUserAccountManager(argsUser.currentUser)
            findNavController().navigate(action)
            //findNavController().navigate(R.id.action_viewUserAccountManager_to_updateUserAccountManager)
        }

        return view

    }

}