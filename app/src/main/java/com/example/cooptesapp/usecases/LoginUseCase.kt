package com.example.cooptesapp.usecases

import com.example.cooptesapp.api.AuthRepository
import com.example.cooptesapp.base.InputValidation
import com.example.cooptesapp.base.isEmailNotValid
import com.example.cooptesapp.base.isPasswordToWeak
import com.example.cooptesapp.models.db.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class LoginUseCase(private val authRepository: AuthRepository) {

    fun login(login: String?, password: String?): Flow<User> =
        flow {
            if (login == null) throw InputValidation.EmptyField()
            if (password == null) throw InputValidation.EmptyPasswordField()
            if (login.isEmpty()) throw InputValidation.EmptyField()
            if (login.isEmailNotValid()) throw InputValidation.WrongEmail()
            if (password.isEmpty()) throw InputValidation.EmptyPasswordField()
            if (password.isPasswordToWeak()) throw InputValidation.WeakPass()
            val userRequest = authRepository.logInWithEmailAndPassword(login, password)
            userRequest?.user.also { user ->
                if (user?.uid != null) emit(
                    User(
                        login,
                        user.uid
                    )
                ) else throw InputValidation.UserNotExist()
            } ?: throw InputValidation.UserNotExist()
        }.flowOn(Dispatchers.IO)

}
