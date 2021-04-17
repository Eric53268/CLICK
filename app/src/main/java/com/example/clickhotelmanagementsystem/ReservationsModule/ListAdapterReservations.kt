package com.example.clickhotelmanagementsystem.ReservationsModule

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.clickhotelmanagementsystem.Database.Reservations.CustomerDetails
import com.example.clickhotelmanagementsystem.R
import kotlinx.android.synthetic.main.custom_row_reservations.view.*

class ListAdapterReservations: RecyclerView.Adapter<ListAdapterReservations.MyViewHolder>(){

    private var reservationList = emptyList<CustomerDetails>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.custom_row_reservations,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = reservationList[position]
        holder.itemView.displayRoomID.text = currentItem.roomID.toString()
        holder.itemView.displayReservationName.text = currentItem.reservationName
        holder.itemView.displayPax.text = currentItem.pax.toString()
        holder.itemView.displayBreakfast.text = currentItem.breakfast.toString()
        holder.itemView.displayDurationOfStay.text = currentItem.checkInDate.toString() + currentItem.checkOutDate.toString()

        holder.itemView.rowLayoutReservations.setOnClickListener {
            val action = ViewReservationsStandardRoomDirections.actionViewReservations2ToEditReservation(currentItem)
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