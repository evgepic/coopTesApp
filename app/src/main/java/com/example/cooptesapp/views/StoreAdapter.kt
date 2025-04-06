package com.example.cooptesapp.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cooptesapp.R
import com.example.cooptesapp.databinding.ItemShipmentBinding
import com.example.cooptesapp.models.domain.Shipment

class StoreAdapter(private val shipments: List<Shipment>) :
    RecyclerView.Adapter<StoreAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding: ItemShipmentBinding = ItemShipmentBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_shipment, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.name.text = shipments[position].name
    }

    override fun getItemCount() = shipments.size
}