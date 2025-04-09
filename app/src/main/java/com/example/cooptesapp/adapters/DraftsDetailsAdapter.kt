package com.example.cooptesapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cooptesapp.R
import com.example.cooptesapp.databinding.ItemDraftsBinding
import com.example.cooptesapp.models.domain.DraftModel

class DraftsDetailsAdapter(var draftShipments: List<DraftModel>) :
    RecyclerView.Adapter<DraftsDetailsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding: ItemDraftsBinding = ItemDraftsBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_drafts, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        draftShipments[position].let {
            holder.binding.draftNameTV.text = it.name
            holder.binding.draftBarcodeTV.text = it.barcode
            holder.binding.draftPriceTV.text = it.price.toString()
            holder.binding.draftDiscountTV.text = it.discount.toString()
            holder.binding.draftAmountTextView.text = it.amount.toString()
        }
    }

    override fun getItemCount() = draftShipments.size
}