package com.example.cooptesapp.base

sealed class InputValidation : Throwable() {
    class EmptyField : InputValidation()
    class EmptyPasswordField : InputValidation()
    class WeakPass : InputValidation()
    class WrongEmail : InputValidation()
    class UserNotExist : InputValidation()
    class PassNotCompare : InputValidation()
}