package com.example.cooptesapp.base

fun String.isPasswordToWeak() = this.length < 6

fun String.isEmailNotValid() = false