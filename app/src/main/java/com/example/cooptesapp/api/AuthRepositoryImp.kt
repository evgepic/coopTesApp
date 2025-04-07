package com.example.cooptesapp.api

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class AuthRepositoryImp :
    AuthRepository {

    override suspend fun logInWithEmailAndPassword(
        email: String,
        password: String
    ): AuthResult? = Firebase.auth.signInWithEmailAndPassword(email, password).await()

}