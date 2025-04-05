package com.example.cooptesapp.views

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.cooptesapp.R
import com.example.cooptesapp.databinding.FragmentAuthorizationBinding
import com.example.cooptesapp.viewmodels.AuthViewModel

class AuthorizationFragment : Fragment(R.layout.fragment_authorization) {

    private var binding: FragmentAuthorizationBinding? = null
    val viewmodel: AuthViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAuthorizationBinding.bind(view)
        binding?.apply {
            loginBtn.setOnClickListener {
                findNavController().navigate(R.id.action_authorizationFragment2_to_registrationFragment2)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}