package com.example.cooptesapp.views

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cooptesapp.R
import com.example.cooptesapp.databinding.FragmentBasketBinding
import com.example.cooptesapp.viewmodels.BasketViewModel

class BasketFragment : Fragment(R.layout.fragment_basket) {

    private var binding: FragmentBasketBinding? = null
    val viewmodel: BasketViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBasketBinding.bind(view)
        val _adapter = BasketAdapter(emptyList(), { viewmodel.deleteItem(it) })
        binding?.apply {
            basketRW.apply {
                layoutManager = LinearLayoutManager(activity)
                adapter = _adapter
            }
            doPay.setOnClickListener {
                viewmodel.doPayment()
            }
        }
        viewmodel.getBasketItems()
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