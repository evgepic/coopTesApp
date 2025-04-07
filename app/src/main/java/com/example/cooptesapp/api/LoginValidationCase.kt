package com.example.cooptesapp.api

sealed class LoginValidationCase : Throwable() {
    class EmptyLoginField : LoginValidationCase()
    class EmptyPasswordField : LoginValidationCase()
    class WeakPassCase : LoginValidationCase()
    class WrongEmail : LoginValidationCase()
    class UserNotExist : LoginValidationCase()
}