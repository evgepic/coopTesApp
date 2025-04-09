package com.example.cooptesapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cooptesapp.R
import com.example.cooptesapp.databinding.ItemShipmentBinding
import com.example.cooptesapp.models.domain.Shipment

class StoreAdapter(
    var shipments: List<Shipment>,
    private val onItemClick: (shipment: Shipment) -> Unit
) :
    RecyclerView.Adapter<StoreAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding: ItemShipmentBinding = ItemShipmentBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_shipment, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        shipments[position].let {
            holder.binding.name.text = it.name
            holder.binding.price.text =
                it.price.amount.toString() + "за ${it.dimension.name}"
            holder.binding.priceWithDiscount.text =
                (it.price.amount - it.price.bonus).toString() + "за ${it.dimension.name}"
            holder.binding.root.setOnClickListener {
                onItemClick(shipments[position])
            }
        }
    }

    override fun getItemCount() = shipments.size
}