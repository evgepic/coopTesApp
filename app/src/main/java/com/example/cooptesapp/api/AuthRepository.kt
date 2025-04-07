package com.example.cooptesapp.api

import com.google.firebase.auth.AuthResult

interface AuthRepository {

    suspend fun logInWithEmailAndPassword(email: String, password: String): AuthResult?

}