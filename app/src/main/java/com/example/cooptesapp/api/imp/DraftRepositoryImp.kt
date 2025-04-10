package com.example.cooptesapp.api.imp

import com.example.cooptesapp.models.domain.DatedShipment
import com.example.cooptesapp.api.DraftRepository
import com.example.cooptesapp.base.InputValidation
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class DraftRepositoryImp() : DraftRepository {

    private val dB = FirebaseFirestore.getInstance()
    private val user = Firebase.auth.currentUser?.uid ?: throw InputValidation.UserNotExist()

    override fun getDraftsList(): Flow<List<DatedShipment>> = flow {
        val x = dB.collection("bills/$user/items").get().await()
        val mapx = x?.documents?.map {
            it?.toObject(DatedShipment::class.java) ?: throw NullPointerException()
        }
        emit(mapx!!)
    }

}