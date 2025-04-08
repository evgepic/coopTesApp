package com.example.cooptesapp.api

import com.example.cooptesapp.models.UserBio
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
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

    override suspend fun createUserBio(userBio: UserBio) {
        val dB = FirebaseFirestore.getInstance()
        dB.collection("users").document(userBio.userId).set(userBio).addOnSuccessListener {
            it
        }.addOnFailureListener {
            it
        }
    }

}