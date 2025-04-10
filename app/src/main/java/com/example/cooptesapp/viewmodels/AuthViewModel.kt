package com.example.cooptesapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.cooptesapp.api.imp.AuthRepositoryImp
import com.example.cooptesapp.usecases.LoginUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class AuthViewModel(
    private val loginValidationUseCase: LoginUseCase
) : ViewModel() {

    val login: MutableLiveData<String> = MutableLiveData("evgepic@gmail.com")
    val password: MutableLiveData<String> = MutableLiveData("123456")
    val authState: MutableLiveData<Boolean> = MutableLiveData()
    val errorHandler: MutableLiveData<Throwable> = MutableLiveData()

    fun logIn() =
        viewModelScope.launch {
            loginValidationUseCase.login(login.value, password.value).flowOn(Dispatchers.IO)
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