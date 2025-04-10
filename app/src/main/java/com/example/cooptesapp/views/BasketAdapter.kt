package com.example.cooptesapp.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cooptesapp.R
import com.example.cooptesapp.database.PriceConverter
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
        shipments[position].let { s ->
            holder.binding.name.text = s.name
            holder.binding.deleteIV.setOnClickListener {
                deleteAction.invoke(s.packId)
            }
            holder.binding.price.text =
                PriceConverter.convertPrice(s.price.amount - s.price.bonus, s.dimension.name)
            holder.binding.totalPrice.text =
                "Сумма: " + PriceConverter.convertPrice((s.price.amount - s.price.bonus) * s.amount)
            holder.binding.amountTV.text = "Кол-в: " + s.amount.toString() + s.dimension.name
        }
    }

    override fun getItemCount() = shipments.size
}