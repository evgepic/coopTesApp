package com.example.cooptesapp.api

import com.example.cooptesapp.models.UserBio
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RegistrationUseCase(private val authRepository: AuthRepository) {

    fun registration(login: String?, password: String?): Flow<User> =
        flow {
            if (login == null) throw LoginValidationCase.EmptyLoginField()
            if (password == null) throw LoginValidationCase.EmptyPasswordField()
            if (login.isEmpty()) throw LoginValidationCase.EmptyLoginField()
            if (login.isEmailNotValid()) throw LoginValidationCase.WrongEmail()
            if (password.isEmpty()) throw LoginValidationCase.EmptyPasswordField()
            if (password.isPasswordToWeak()) throw LoginValidationCase.WeakPassCase()
            val userRequest = authRepository.registration(login, password)
            userRequest.user.also { user ->
                if (user?.uid != null) {
                    authRepository.createUserBio(
                        UserBio("N", "SN", "SURN", user.uid)
                    )
                }
            }
        }

    fun createBio(name: String, secondName: String, surname: String) {

    }

}