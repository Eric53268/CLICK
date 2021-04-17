package com.example.clickhotelmanagementsystem.AccountsModule

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.clickhotelmanagementsystem.Database.Accounts.EditOwnProfile
import com.example.clickhotelmanagementsystem.R
import kotlinx.android.synthetic.main.custom_row_accounts.view.*

class ListAdapterAccounts: RecyclerView.Adapter<ListAdapterAccounts.MyViewHolder>() {

    private var userList = emptyList<EditOwnProfile>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.custom_row_accounts,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.itemView.displayID.text = currentItem.uID.toString()
        holder.itemView.displayName.text = currentItem.firstName
        holder.itemView.displayPosition.text = currentItem.department.toString()
        holder.itemView.displayDepartment.text = currentItem.position.toString()

        holder.itemView.rowLayoutAccounts.setOnClickListener {
            val action = ListUsersManagerFragmentDirections.actionListUsersManagerToViewUserAccountManager(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(user: List<EditOwnProfile>){
        this.userList = user
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return userList.size
    }

}