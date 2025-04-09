package com.example.cooptesapp.api.imp

import com.example.cooptesapp.api.AuthRepository
import com.example.cooptesapp.models.db.UserBio
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.tasks.await

class AuthRepositoryImp :
    AuthRepository {

    override suspend fun logInWithEmailAndPassword(
        email: String,
        password: String
    ): AuthResult =
        Firebase.auth.signInWithEmailAndPassword(email, password).await()

    override suspend fun registration(
        email: String,
        password: String
    ): AuthResult =
        Firebase.auth.createUserWithEmailAndPassword(email, password).await()

    override suspend fun createUserBio(userBio: UserBio): Flow<UserBio> = flow {
        val dB = FirebaseFirestore.getInstance()
        val user = dB.collection("users").document(userBio.userId).set(userBio).await()
        flowOf(user)
    }
}