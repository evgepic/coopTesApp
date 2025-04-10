package com.example.cooptesapp.views

import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.cooptesapp.R
import com.example.cooptesapp.base.InputValidation
import com.example.cooptesapp.databinding.FragmentAuthorizationBinding
import com.example.cooptesapp.viewmodels.AuthViewModel

class AuthorizationFragment : BaseFragment(R.layout.fragment_authorization) {

    private var binding: FragmentAuthorizationBinding? = null
    val viewmodel: AuthViewModel by viewModels { AuthViewModel.Factory }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAuthorizationBinding.bind(view)
        binding?.apply {
            loginBtn.setOnClickListener {
                baseUiActions.startLoading()
                viewmodel.logIn()
            }
            registrationBtn.setOnClickListener {
                findNavController().navigate(R.id.action_authFragment_to_registrationFragment)
            }
            loginET.apply {
                doAfterTextChanged {
                    viewmodel.login.value = it.toString()
                }
            }
            passET.apply {
                doAfterTextChanged {
                    viewmodel.password.value = it.toString()
                }
            }
        }
        viewmodel.errorHandler.observe(viewLifecycleOwner, {
            this.baseUiActions.showError(it ?: throw InputValidation.UserNotExist())
            viewmodel.errorHandler.value = null
        })
        viewmodel.authState.observe(viewLifecycleOwner, {
            baseUiActions.endLoading()
            it?.let { state ->
                if (state) {
                    findNavController().navigate(R.id.action_authFragment_to_storeFragment)
                }
            }
        })
    }

    override fun clearBinding() {
        binding = null
    }

}