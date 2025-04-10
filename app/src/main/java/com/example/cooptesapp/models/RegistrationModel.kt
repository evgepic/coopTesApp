package com.example.cooptesapp.models

data class RegistrationModel(
    val login: String?,
    val password: String?,
    val repeatPassword: String?,
    val name: String?,
    val secondName: String?,
    val surname: String?
)