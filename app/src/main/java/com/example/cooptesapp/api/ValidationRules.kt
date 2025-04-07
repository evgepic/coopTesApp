package com.example.cooptesapp.api

fun String.isPasswordToWeak() = this.length >= 4

fun String.isEmailNotValid() = false