package com.example.cooptesapp.views

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.cooptesapp.R
import com.example.cooptesapp.databinding.FragmentRegistrationBinding

class RegistrationFragment : Fragment(R.layout.fragment_registration) {

    private var binding: FragmentRegistrationBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegistrationBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
