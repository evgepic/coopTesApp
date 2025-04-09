package com.example.cooptesapp.views

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.cooptesapp.R
import com.example.cooptesapp.base.ErrorHandler
import com.example.cooptesapp.base.LoadingAction
import com.example.cooptesapp.databinding.FragmentRegistrationBinding
import com.example.cooptesapp.viewmodels.RegistrationViewModel


class RegistrationFragment : Fragment(R.layout.fragment_registration) {

    private var binding: FragmentRegistrationBinding? = null
    val viewmodel: RegistrationViewModel by viewModels { RegistrationViewModel.Factory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegistrationBinding.bind(view)
        viewmodel.errorHandler.observe(viewLifecycleOwner, {
            ((this@RegistrationFragment.activity) as LoadingAction).endLoading()
            (activity as ErrorHandler).handle(it)
        })
        binding?.apply {
            regBtn.setOnClickListener {
                ((this@RegistrationFragment.activity) as LoadingAction).startLoading()
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
            viewmodel.user.observe(viewLifecycleOwner, {
                ((this@RegistrationFragment.activity) as LoadingAction).endLoading()
                val toast = Toast.makeText(
                    this@RegistrationFragment.context,
                    "Регистрация прошла успешно",
                    Toast.LENGTH_LONG
                )
                toast.show()
                findNavController().popBackStack()
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
