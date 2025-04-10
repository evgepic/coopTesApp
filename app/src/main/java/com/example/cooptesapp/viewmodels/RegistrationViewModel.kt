package com.example.cooptesapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.cooptesapp.api.imp.AuthRepositoryImp
import com.example.cooptesapp.models.RegistrationModel
import com.example.cooptesapp.models.db.User
import com.example.cooptesapp.usecases.RegistrationUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class RegistrationViewModel(private val registrationUseCase: RegistrationUseCase) : ViewModel() {

    val login: MutableLiveData<String> = MutableLiveData("evgepic@gmail.com")
    val password: MutableLiveData<String> = MutableLiveData("123456")
    val repeatPass: MutableLiveData<String> = MutableLiveData("evgepic")
    val name: MutableLiveData<String> = MutableLiveData("evgepic")
    val errorHandler: MutableLiveData<Throwable> = MutableLiveData()
    val user: MutableLiveData<User> = MutableLiveData()

    fun registration() {
        val registrationModel = RegistrationModel(
            login = login.value,
            password = password.value,
            repeatPassword = repeatPass.value,
            name = name.value,
            secondName = name.value,
            surname = name.value
        )
        viewModelScope.launch {
            registrationUseCase.registration(registrationModel).catch {
                errorHandler.value = it
            }.collect {
                user.value = it
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