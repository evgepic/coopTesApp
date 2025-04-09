package com.example.cooptesapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.cooptesapp.api.imp.AuthRepositoryImp
import com.example.cooptesapp.models.db.User
import com.example.cooptesapp.usecases.RegistrationUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegistrationViewModel(private val registrationUseCase: RegistrationUseCase) : ViewModel() {

    val login: MutableLiveData<String> = MutableLiveData("evgepic@gmail.com")
    val password: MutableLiveData<String> = MutableLiveData("evgepic")
    val repeatPass: MutableLiveData<String> = MutableLiveData("evgepic")
    val name: MutableLiveData<String> = MutableLiveData("evgepic")
    val errorHandler: MutableLiveData<Throwable> = MutableLiveData()
    val user: MutableLiveData<User> = MutableLiveData()

    fun registration() {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                registrationUseCase.registration(
                    login = login.value,
                    password = password.value,
                    repeatPassword = repeatPass.value,
                    name = name.value,
                    secondName = name.value,
                    surname = name.value
                ).flowOn(Dispatchers.IO).catch {
                    errorHandler.value = it
                }.collect(
                    {
                        user.value = it
                    }
                )
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