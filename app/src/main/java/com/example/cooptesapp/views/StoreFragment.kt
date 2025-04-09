package com.example.cooptesapp.views

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cooptesapp.R
import com.example.cooptesapp.adapters.StoreAdapter
import com.example.cooptesapp.databinding.FragmentStoreBinding
import com.example.cooptesapp.models.domain.BasketDialogModel
import com.example.cooptesapp.models.domain.Shipment
import com.example.cooptesapp.viewmodels.StoreViewModel


class StoreFragment : Fragment(R.layout.fragment_store) {

    private var binding: FragmentStoreBinding? = null
    val viewmodel: StoreViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentStoreBinding.bind(view)
        val _adapter = StoreAdapter(emptyList(), { showDialog(it) })
        binding?.apply {
            shipmentsRW.apply {
                layoutManager = LinearLayoutManager(activity)
                adapter = _adapter
            }
            profileBtn.setOnClickListener {
                findNavController().navigate(R.id.action_storeFragment_to_profileFragment)
            }
            basketBtn.setOnClickListener {
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
        viewmodel.addToBasket(basketDialogModel.packId, basketDialogModel.amount)
    }

    private fun showDialog(shipment: Shipment) {
        val dialogFragment = StoreDialogFragment({ addToBasket(it) })
        val args = Bundle()
        args.putString("name", shipment.name)
        args.putLong("amount", shipment.price.amount)
        args.putLong("bonus", shipment.price.bonus)
        args.putLong("packId", shipment.packId)
        args.putString("unit", shipment.dimension.name)
        dialogFragment.arguments = args
        dialogFragment.show(parentFragmentManager, "MyDia")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}