package com.example.cooptesapp.views

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cooptesapp.R
import com.example.cooptesapp.databinding.FragmentStoreBinding
import com.example.cooptesapp.models.domain.Shipment

class StoreFragment : Fragment(R.layout.fragment_store) {

    private var binding: FragmentStoreBinding? = null
    private val list = listOf<Shipment>(
        Shipment("One"),
        Shipment("TWO"),
        Shipment("THUHRE"),
        Shipment("LDMLSDM"),
        Shipment("d,slMdk"),
        Shipment("lmd,slmd"),
        Shipment("LSMDlm"),
        Shipment("dmsldms"),
        Shipment("D<LSMDm")
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentStoreBinding.bind(view)
        val _adapter = StoreAdapter(list)
        binding?.apply {
            shipmentsRW.apply {
                layoutManager = LinearLayoutManager(activity)
                adapter = _adapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}