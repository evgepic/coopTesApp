package com.example.cooptesapp.views

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cooptesapp.R
import com.example.cooptesapp.adapters.StoreAdapter
import com.example.cooptesapp.databinding.FragmentStoreBinding
import com.example.cooptesapp.models.domain.BasketDialogModel
import com.example.cooptesapp.models.domain.Shipment
import com.example.cooptesapp.viewmodels.StoreViewModel


class StoreFragment : BaseFragment(R.layout.fragment_store) {

    private var binding: FragmentStoreBinding? = null
    val viewmodel: StoreViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentStoreBinding.bind(view)
        val _adapter = StoreAdapter(emptyList(), { showDialog(it) })
        binding?.apply {
            shipmentsRW.apply {
                addItemDecoration(
                    DividerItemDecoration(context, RecyclerView.VERTICAL).apply {
                        context.getDrawable(R.drawable.rw_devider)?.let { setDrawable(it) }
                    }
                )
                layoutManager = LinearLayoutManager(activity)
                adapter = _adapter
            }
            profileIV.setOnClickListener {
                findNavController().navigate(R.id.action_storeFragment_to_profileFragment)
            }
            basketIV.setOnClickListener {
                findNavController().navigate(R.id.action_storeFragment_to_basketFragment)
            }
        }
        viewmodel.getShipmentsList()
        viewmodel.list.observe(viewLifecycleOwner, {
            _adapter.shipments = it
            _adapter.notifyDataSetChanged()
        })
    }

    private fun addToBasket(basketDialogModel: BasketDialogModel) {
        if (basketDialogModel.amount < 100000) {
            viewmodel.addToBasket(basketDialogModel.packId, basketDialogModel.amount)
        } else
            baseUiActions.showError("Cумма слишком большая")
    }

    private fun showDialog(shipment: Shipment) {
        val dialogFragment = StoreDialogFragment{ addToBasket(it) }
        val args = Bundle()
        args.putString("name", shipment.name)
        args.putLong("amount", shipment.price.amount)
        args.putLong("bonus", shipment.price.bonus)
        args.putLong("packId", shipment.packId)
        args.putString("unit", shipment.dimension.name)
        dialogFragment.arguments = args
        dialogFragment.show(parentFragmentManager, "MyDia")
    }

    override fun clearBinding() {
        binding = null
    }
}