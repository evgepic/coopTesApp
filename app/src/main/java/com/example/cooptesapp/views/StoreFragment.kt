package com.example.cooptesapp.views

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cooptesapp.R
import com.example.cooptesapp.databinding.FragmentStoreBinding
import com.example.cooptesapp.viewmodels.StoreViewModel

class StoreFragment : Fragment(R.layout.fragment_store) {

    private var binding: FragmentStoreBinding? = null
    val viewmodel: StoreViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentStoreBinding.bind(view)
        val _adapter = StoreAdapter(emptyList())
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

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}