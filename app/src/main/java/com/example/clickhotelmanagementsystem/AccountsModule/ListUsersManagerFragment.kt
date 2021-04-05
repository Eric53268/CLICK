package com.example.clickhotelmanagementsystem.AccountsModule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.clickhotelmanagementsystem.Database.UserViewModel
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
            val adapter = ListAdapter()
            val recyclerView = view.view_user
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())

            //UserViewModel
            mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
            mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer { user -> adapter.setData(user)})

            binding.addButton.setOnClickListener {
                findNavController().navigate(R.id.action_list_users_manager_to_add_user_account_manager)
            }
            return view
        }
}
