package com.example.cooptesapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.cooptesapp.api.AuthRepositoryImp
import com.example.cooptesapp.api.LoginValidationUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AuthViewModel(
    private val loginValidationUseCase: LoginValidationUseCase
) : ViewModel() {

    val login: MutableLiveData<String> = MutableLiveData("12")
    val password: MutableLiveData<String> = MutableLiveData("12")

    fun logIn() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                loginValidationUseCase.validateLogin(login.value, password.value)
            }.catch {
                it
            }.collect({ user ->
                user
            })
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                AuthViewModel(
                    LoginValidationUseCase(AuthRepositoryImp())
                )
            }
        }
    }
}