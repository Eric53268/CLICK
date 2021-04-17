package com.example.clickhotelmanagementsystem.ReservationsModuleV2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.clickhotelmanagementsystem.Database.Reservations.CustomerDetails
import com.example.clickhotelmanagementsystem.R
import kotlinx.android.synthetic.main.custom_row_reservations_v2.view.*

class ListAdapterReservationsV2: RecyclerView.Adapter<ListAdapterReservationsV2.MyReservationsViewHolder>() {

    private var reservationList = emptyList<CustomerDetails>()

    class MyReservationsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyReservationsViewHolder {
        return MyReservationsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.custom_row_reservations_v2,
                parent,
                false))
    }

    override fun onBindViewHolder(holder: MyReservationsViewHolder, position: Int) {
        val currentReservation = reservationList[position]
        holder.itemView.displayReservationNameV2.text = currentReservation.reservationName.toString()
        holder.itemView.displayPaxV2.text = currentReservation.pax.toString()
        holder.itemView.displayCheckInDateV2.text = currentReservation.checkInDate.toString()

        holder.itemView.rowLayoutReservationsV2.setOnClickListener {
            val action = ViewAllReservationsV2Directions.actionViewAllReservationsV2ToViewReservationsV2(currentReservation)
            holder.itemView.findNavController().navigate(action)
        }

    }

    fun setData(reservation: List<CustomerDetails>){
        this.reservationList = reservation
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return reservationList.size
    }

}