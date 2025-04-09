package com.example.cooptesapp.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cooptesapp.R
import com.example.cooptesapp.databinding.ItemBasketBinding
import com.example.cooptesapp.models.domain.Shipment

class BasketAdapter(
    var shipments: List<Shipment>,
    private val deleteAction: (packId: Long) -> Unit
) :
    RecyclerView.Adapter<BasketAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding: ItemBasketBinding = ItemBasketBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_basket, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.name.text = shipments[position].name
        holder.binding.deleteBtn.setOnClickListener {
            deleteAction.invoke(shipments[position].packId)
        }
        holder.binding.price.text = shipments[position].price.amount.toString()
        holder.binding.priceWithDiscount.text = shipments[position].price.bonus.toString()
        holder.binding.amountTV.text = shipments[position].amount.toString()
    }

    override fun getItemCount() = shipments.size
}