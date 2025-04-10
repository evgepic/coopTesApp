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
            val msg = when (it) {
                is InputValidation.EmptyField -> "Пустое поле"
                is InputValidation.EmptyPasswordField -> "Пустое поле пароль"
                is InputValidation.WeakPass ->
                    "Пароль должен быть больше 6 символов"

                is InputValidation.WrongEmail -> "Некорректный email"
                is InputValidation.UserNotExist -> "Пользователя не существует"
                is InputValidation.PassNotCompare -> "Пароли не совпадают"
                else -> {
                    it.message.toString()
                }
            }
            this.baseUiActions?.showError(msg)
        })
        viewmodel.authState.observe(viewLifecycleOwner, {
            findNavController().navigate(R.id.action_authFragment_to_storeFragment)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}