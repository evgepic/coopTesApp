package com.example.cooptesapp.usecases

import com.example.cooptesapp.api.AuthRepository
import com.example.cooptesapp.base.InputValidation
import com.example.cooptesapp.base.isEmailNotValid
import com.example.cooptesapp.base.isPasswordToWeak
import com.example.cooptesapp.models.RegistrationModel
import com.example.cooptesapp.models.db.User
import com.example.cooptesapp.models.db.UserBio
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RegistrationUseCase(private val authRepository: AuthRepository) {

    fun registration(
        registrationModel: RegistrationModel
    ) = flow {
        if (registrationModel.login == null) throw InputValidation.EmptyField()
        if (registrationModel.password == null) throw InputValidation.EmptyPasswordField()
        if (registrationModel.repeatPassword != registrationModel.repeatPassword) throw InputValidation.PassNotCompare()
        if (registrationModel.login.isEmpty()) throw InputValidation.EmptyField()
        if (registrationModel.login.isEmailNotValid()) throw InputValidation.WrongEmail()
        if (registrationModel.password.isEmpty()) throw InputValidation.EmptyPasswordField()
        if (registrationModel.password.isPasswordToWeak()) throw InputValidation.WeakPass()
        if (registrationModel.name == null) throw InputValidation.EmptyField()
        if (registrationModel.secondName == null) throw InputValidation.EmptyField()
        if (registrationModel.surname == null) throw InputValidation.EmptyField()
        val userRequest =
            authRepository.registration(registrationModel.login, registrationModel.password)
        userRequest.user.also { user ->
            if (user?.uid != null) {
                authRepository.createUserBio(
                    UserBio(
                        registrationModel.name,
                        registrationModel.secondName,
                        registrationModel.surname,
                        user.uid
                    )
                )
                emit(User(registrationModel.name, user.uid))
            } else
                throw InputValidation.UserNotExist()
        }
    }.flowOn(Dispatchers.IO)

}