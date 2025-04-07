package com.example.cooptesapp.api

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LoginValidationUseCase(private val authRepository: AuthRepository) {

    suspend fun validateLogin(login: String?, password: String?): Flow<User> =
        flow {
            if (login == null) throw LoginValidationCase.EmptyLoginField()
            if (password == null) throw LoginValidationCase.EmptyPasswordField()
            if (login.isEmpty()) throw LoginValidationCase.EmptyLoginField()
            if (login.isEmailNotValid()) throw LoginValidationCase.WrongEmail()
            if (password.isEmpty()) throw LoginValidationCase.EmptyPasswordField()
            if (password.isPasswordToWeak()) throw LoginValidationCase.WeakPassCase()
            authRepository.logInWithEmailAndPassword(login, password)
        }

}