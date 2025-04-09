package com.example.cooptesapp.usecases

import com.example.cooptesapp.api.AuthRepository
import com.example.cooptesapp.base.InputValidation
import com.example.cooptesapp.base.isEmailNotValid
import com.example.cooptesapp.base.isPasswordToWeak
import com.example.cooptesapp.models.db.User
import com.example.cooptesapp.models.db.UserBio
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RegistrationUseCase(private val authRepository: AuthRepository) {

    fun registration(
        login: String?,
        password: String?,
        repeatPassword: String?,
        name: String?,
        secondName: String?,
        surname: String?
    ): Flow<User> =
        flow {
            if (login == null) throw InputValidation.EmptyField()
            if (password == null) throw InputValidation.EmptyPasswordField()
            if (repeatPassword != repeatPassword) throw InputValidation.PassNotCompare()
            if (login.isEmpty()) throw InputValidation.EmptyField()
            if (login.isEmailNotValid()) throw InputValidation.WrongEmail()
            if (password.isEmpty()) throw InputValidation.EmptyPasswordField()
            if (password.isPasswordToWeak()) throw InputValidation.WeakPass()
            if (name == null) throw InputValidation.EmptyField()
            if (secondName == null) throw InputValidation.EmptyField()
            if (surname == null) throw InputValidation.EmptyField()
            val userRequest = authRepository.registration(login, password)
            userRequest.user.also { user ->
                if (user?.uid != null) {
                    authRepository.createUserBio(
                        UserBio(name, secondName, surname, user.uid)
                    )
                }
            }
        }

}