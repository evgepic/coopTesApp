package com.example.cooptesapp.views

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cooptesapp.R
import com.example.cooptesapp.adapters.DraftsDetailsAdapter
import com.example.cooptesapp.databinding.FragmentDraftsBinding
import com.example.cooptesapp.models.domain.DraftModel

class DraftsFragment : Fragment(R.layout.fragment_drafts) {

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
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}