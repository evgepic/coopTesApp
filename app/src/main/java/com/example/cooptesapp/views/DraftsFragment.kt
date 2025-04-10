package com.example.cooptesapp.views

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cooptesapp.R
import com.example.cooptesapp.adapters.DraftsDetailsAdapter
import com.example.cooptesapp.databinding.FragmentDraftsBinding
import com.example.cooptesapp.models.domain.DraftModel

class DraftsFragment : BaseFragment(R.layout.fragment_drafts) {

    private var binding: FragmentDraftsBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDraftsBinding.bind(view)
        val _adapter = DraftsDetailsAdapter(emptyList())
        val list = arguments?.getParcelableArrayList<DraftModel>("list")
        binding?.apply {
            draftsRW.apply {
                layoutManager = LinearLayoutManager(activity)
                adapter = _adapter
                _adapter.draftShipments = list?.toList() ?: emptyList()
                _adapter.notifyDataSetChanged()
            }.addItemDecoration(
                DividerItemDecoration(context, RecyclerView.VERTICAL).apply {
                    context?.getDrawable(R.drawable.rw_devider)?.let { setDrawable(it) }
                }
            )
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}