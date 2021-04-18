package com.example.clickhotelmanagementsystem.AccountsModule

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.clickhotelmanagementsystem.Database.Accounts.UserViewModel
import com.example.clickhotelmanagementsystem.Manager.MainPageManager
import com.example.clickhotelmanagementsystem.R
import com.example.clickhotelmanagementsystem.databinding.FragmentListUsersManagerBinding
import kotlinx.android.synthetic.main.fragment_list_users_manager.view.*


class ListUsersManagerFragment : Fragment()  {

    private lateinit var binding: FragmentListUsersManagerBinding
    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list_users_manager, container, false)
        binding = FragmentListUsersManagerBinding.bind(view)

            //Recyclerview
            val adapter = ListAdapterAccounts()
            val recyclerView = view.view_user
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())

            //UserViewModel
            mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
            mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer { user -> adapter.setData(user)})

            binding.addButton.setOnClickListener {
                findNavController().navigate(R.id.action_list_users_manager_to_add_user_account_manager)
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
