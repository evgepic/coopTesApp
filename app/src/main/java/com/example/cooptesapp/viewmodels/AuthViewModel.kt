package com.example.cooptesapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.cooptesapp.api.imp.AuthRepositoryImp
import com.example.cooptesapp.base.BaseViewModel
import com.example.cooptesapp.base.SingleLiveData
import com.example.cooptesapp.usecases.LoginUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class AuthViewModel(
    private val loginValidationUseCase: LoginUseCase
) : BaseViewModel() {

    val login: MutableLiveData<String> = MutableLiveData()
    val password: MutableLiveData<String> = MutableLiveData()
    val authState: SingleLiveData<Boolean> = SingleLiveData()

    fun logIn() =
        viewModelScope.launch {
            loginValidationUseCase.login(login.value, password.value)
                .catch {
                    errorHandler.value = it
                }
                .collect({
                    authState.value = true
                })
        }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                AuthViewModel(
                    LoginUseCase(AuthRepositoryImp())
                )
            }
        }
    }
}