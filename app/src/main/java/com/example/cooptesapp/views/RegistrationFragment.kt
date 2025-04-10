package com.example.cooptesapp.views

import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.cooptesapp.R
import com.example.cooptesapp.databinding.FragmentRegistrationBinding
import com.example.cooptesapp.viewmodels.RegistrationViewModel


class RegistrationFragment : BaseFragment(R.layout.fragment_registration) {

    private var binding: FragmentRegistrationBinding? = null
    val viewmodel: RegistrationViewModel by viewModels { RegistrationViewModel.Factory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegistrationBinding.bind(view)
        viewmodel.errorHandler.observe(viewLifecycleOwner, {
            baseUiActions?.showError(it.message ?: "Unknown error")
        })
        binding?.apply {
            regBtn.setOnClickListener {
                baseUiActions?.startLoading()
                viewmodel.registration()
            }
            passET.apply {
                doAfterTextChanged {
                    viewmodel.password.value = it.toString()
                }
            }
            repeartPassET.apply {
                doAfterTextChanged {
                    viewmodel.repeatPass.value = it.toString()
                }
            }
            nameEY.apply {
                doAfterTextChanged {
                    viewmodel.name.value = it.toString()
                }
            }
            loginET.apply {
                doAfterTextChanged {
                    viewmodel.login.value = it.toString()
                }
            }
            viewmodel.user.observe(viewLifecycleOwner, {
                baseUiActions?.endLoading()
                baseUiActions?.showToast(R.string.succsesRegistration)
                findNavController().popBackStack()
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
