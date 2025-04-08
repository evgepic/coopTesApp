package com.example.cooptesapp.api

fun String.isPasswordToWeak() = this.length < 6

fun String.isEmailNotValid() = false