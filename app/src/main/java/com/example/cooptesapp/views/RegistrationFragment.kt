package com.example.cooptesapp.views

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.cooptesapp.R
import com.example.cooptesapp.databinding.FragmentRegistrationBinding
import com.example.cooptesapp.viewmodels.RegistrationViewModel

class RegistrationFragment : Fragment(R.layout.fragment_registration) {

    private var binding: FragmentRegistrationBinding? = null
    val viewmodel: RegistrationViewModel by viewModels { RegistrationViewModel.Factory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegistrationBinding.bind(view)
        binding?.apply {
            loginBtn.setOnClickListener {
                viewmodel.registration()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
