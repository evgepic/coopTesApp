package com.example.cooptesapp.views

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cooptesapp.R
import com.example.cooptesapp.database.PriceConverter
import com.example.cooptesapp.databinding.FragmentBasketBinding
import com.example.cooptesapp.viewmodels.BasketViewModel

class BasketFragment : BaseFragment(R.layout.fragment_basket) {

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
            }.addItemDecoration(
                DividerItemDecoration(context, RecyclerView.VERTICAL).apply {
                    context?.getDrawable(R.drawable.rw_devider)?.let { setDrawable(it) }
                }
            )
            doPay.setOnClickListener {
                if (viewmodel.list.value?.isNotEmpty() == true) {
                    viewmodel.doPayment()
                }
            }
        }
        viewmodel.getBasketItems()
        viewmodel.list.observe(viewLifecycleOwner, {
            _adapter.shipments = it
            _adapter.notifyDataSetChanged()
        })
        viewmodel.doPaymentAmount.observe(viewLifecycleOwner, {
            val sum = PriceConverter.convertPrice(it)
            this.baseUiActions.showError("Вы успешно купили товаров на сумму $sum")
        })
    }


    override fun clearBinding() {
        binding = null
    }
}