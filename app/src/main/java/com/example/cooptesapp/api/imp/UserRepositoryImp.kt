package com.example.cooptesapp.api.imp

import com.example.cooptesapp.api.UserRepository
import com.example.cooptesapp.base.InputValidation
import com.example.cooptesapp.models.db.UserBio
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class UserRepositoryImp : UserRepository {

    private val dB = FirebaseFirestore.getInstance()
    private val user = Firebase.auth.currentUser?.uid ?: throw InputValidation.UserNotExist()

    override fun getUserDataById() =
        flow {
            val x = dB.collection("users").document(user).get().await()
            val user = x?.toObject(UserBio::class.java) ?: throw NullPointerException()
            emit(user)
        }

}