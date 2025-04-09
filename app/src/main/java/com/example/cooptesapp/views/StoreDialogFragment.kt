package com.example.cooptesapp.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.cooptesapp.R
import com.example.cooptesapp.databinding.DialogFragmentStoreBinding
import com.example.cooptesapp.models.domain.BasketDialogModel


class StoreDialogFragment(private val resultAction: (basketDialogModel: BasketDialogModel) -> Unit) :
    DialogFragment() {

    private var binding: DialogFragmentStoreBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView: View = inflater.inflate(R.layout.dialog_fragment_store, container, false)
        dialog!!.setTitle("Simple Dialog")
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val name = arguments?.getString("name")
        val amount = arguments?.getLong("amount")
        val bonus = arguments?.getLong("bonus")
        val packId = arguments?.getLong("packId")
        val dimension = arguments?.getString("unit")
        binding = DialogFragmentStoreBinding.bind(view)
        binding?.apply {
            nameTV.text = name
            amountTV.text = "Цена : $amount за $dimension"
            discountTV.text = "Скидка $bonus  за $dimension"
            amountET.hint = dimension
            addToBasketBtn.setOnClickListener {
                amountET.text.toString().let {
                    if (packId != null && it.isNotEmpty() && it.first().toString() != "0") {
                        amountET.text.toString().isNotEmpty()
                        resultAction.invoke(BasketDialogModel(packId, it.toLong()))
                        dismiss()
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}
