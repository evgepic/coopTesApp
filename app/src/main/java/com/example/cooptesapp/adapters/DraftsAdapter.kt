package com.example.cooptesapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cooptesapp.R
import com.example.cooptesapp.models.domain.DatedShipment
import com.example.cooptesapp.databinding.ItemDraftBinding
import com.example.cooptesapp.models.domain.DraftModel

class DraftsAdapter(
    var draftShipments: List<DatedShipment>,
    private val onItemClick: (list: List<DraftModel>) -> Unit
) :
    RecyclerView.Adapter<DraftsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding: ItemDraftBinding = ItemDraftBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_draft, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        draftShipments[position].let {
            holder.binding.data.text = it.date + "  " + it.time
        }
        holder.binding.root.setOnClickListener {
            onItemClick(draftShipments[position].list)
        }
    }

    override fun getItemCount() = draftShipments.size
}