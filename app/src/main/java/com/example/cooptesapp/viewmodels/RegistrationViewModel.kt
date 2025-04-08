package com.example.cooptesapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.cooptesapp.api.AuthRepositoryImp
import com.example.cooptesapp.api.RegistrationUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegistrationViewModel(private val registrationUseCase: RegistrationUseCase) : ViewModel() {

    val login: MutableLiveData<String> = MutableLiveData("evgepic@gmail.com")
    val password: MutableLiveData<String> = MutableLiveData("123456")

    fun registration() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                registrationUseCase.registration(login.value, password.value)
                    .catch {
                        it
                    }
                    .collect({
                        it
                    })
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                RegistrationViewModel(
                    RegistrationUseCase(AuthRepositoryImp())
                )
            }
        }
    }

}