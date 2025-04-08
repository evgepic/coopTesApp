package com.example.cooptesapp.api

import com.example.cooptesapp.models.UserBio
import com.google.firebase.auth.AuthResult

interface AuthRepository {

    suspend fun logInWithEmailAndPassword(email: String, password: String): AuthResult?

    suspend fun registration(
        email: String,
        password: String
    ): AuthResult

    suspend fun createUserBio(userBio: UserBio)
}