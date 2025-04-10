package com.example.cooptesapp.views

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cooptesapp.R
import com.example.cooptesapp.adapters.DraftsAdapter
import com.example.cooptesapp.databinding.FragmentProfileBinding
import com.example.cooptesapp.models.domain.DraftModel
import com.example.cooptesapp.viewmodels.ProfileViewModel

class ProfileFragment : BaseFragment(R.layout.fragment_profile) {

    private var binding: FragmentProfileBinding? = null
    private val viewModel: ProfileViewModel by viewModels { ProfileViewModel.Factory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileBinding.bind(view)
        val _adapter = DraftsAdapter(emptyList(), { showInnerShipments(it) })
        binding?.apply {
            draftsRW.apply {
                layoutManager = LinearLayoutManager(activity)
                adapter = _adapter
            }
        }
        viewModel.getUser()
        viewModel.getDraftsInfo()
        viewModel.list.observe(viewLifecycleOwner, {
            _adapter.draftShipments = it
            _adapter.notifyDataSetChanged()
        })
        viewModel.profileData.observe(viewLifecycleOwner, {
            binding?.userName?.text = it
        })
    }

    fun showInnerShipments(list: List<DraftModel>) {
        val bundle = bundleOf(Pair("list", list))
        findNavController().navigate(R.id.action_profileFragment_to_draftsFragment, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun clearBinding() {
        binding = null
    }

}