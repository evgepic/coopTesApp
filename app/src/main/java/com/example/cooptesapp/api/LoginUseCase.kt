package com.example.cooptesapp.api

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LoginUseCase(private val authRepository: AuthRepository) {

    suspend fun login(login: String?, password: String?): Flow<User> =
        flow {
            if (login == null) throw LoginValidationCase.EmptyLoginField()
            if (password == null) throw LoginValidationCase.EmptyPasswordField()
            if (login.isEmpty()) throw LoginValidationCase.EmptyLoginField()
            if (login.isEmailNotValid()) throw LoginValidationCase.WrongEmail()
            if (password.isEmpty()) throw LoginValidationCase.EmptyPasswordField()
            if (password.isPasswordToWeak()) throw LoginValidationCase.WeakPassCase()
            val userRequest = authRepository.logInWithEmailAndPassword(login, password)
            userRequest?.user.also { user ->
                if (user?.uid != null) emit(
                    User(
                        login,
                        user.uid
                    )
                ) else throw LoginValidationCase.UserNotExist()
            } ?: throw LoginValidationCase.UserNotExist()
        }

}
