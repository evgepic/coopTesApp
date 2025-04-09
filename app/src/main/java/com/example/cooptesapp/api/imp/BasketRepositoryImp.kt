package com.example.cooptesapp.api.imp

import com.example.cooptesapp.models.domain.BasketModel
import com.example.cooptesapp.api.BasketRepository
import com.example.cooptesapp.base.InputValidation
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class BasketRepositoryImp : BasketRepository {

    private val dB = FirebaseFirestore.getInstance()
    private val user = Firebase.auth.currentUser?.uid ?: throw InputValidation.UserNotExist()

    override fun addToBasket(packId: Long, amount: Long) {
        dB.collection("baskets/$user/items").whereEqualTo("shipmentId", packId).get()
            .addOnSuccessListener { doc ->
                if (doc.isEmpty) {
                    dB.collection("baskets/$user/items").document()
                        .set(BasketModel(user, packId, amount))
                } else {
                    doc.documents.first().let { sameElem ->
                        val _amount =
                            sameElem.toObject(BasketModel::class.java)?.amount ?: 0
                        dB.collection("baskets/$user/items")
                            .document(sameElem.id).update(
                                "amount", _amount + amount
                            )
                    }
                }
            }

    }

    override fun deleteFromBasket(packId: Long) {
        dB.collection("baskets/$user/items").whereEqualTo("shipmentId", packId).get()
            .addOnSuccessListener { doc ->
                doc.documents.first().let { sameElem ->
                    dB.collection("baskets/$user/items")
                        .document(sameElem.id).delete()
                }
            }
    }


    override suspend fun getFromBasket() = flow<List<BasketModel>> {
        val x = dB.collection("baskets/$user/items").get().await()
        val mapx = x?.documents?.map {
            it.toObject(BasketModel::class.java) ?: throw NullPointerException()
        }
        emit(mapx!!)
    }
}

