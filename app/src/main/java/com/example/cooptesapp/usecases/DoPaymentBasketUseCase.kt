package com.example.cooptesapp.usecases

import com.example.cooptesapp.api.BasketRepository
import com.example.cooptesapp.api.DataBaseRepository
import com.example.cooptesapp.base.InputValidation
import com.example.cooptesapp.models.domain.DatedShipment
import com.example.cooptesapp.models.domain.DraftModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class DoPaymentBasketUseCase(
    private val basketRepository: BasketRepository,
    private val database: DataBaseRepository
) {

    private val dB = FirebaseFirestore.getInstance()
    private val user = Firebase.auth.currentUser?.uid ?: throw InputValidation.UserNotExist()

    fun doPayment(time: String, date: String) = flow<Long> {
        basketRepository.getFromBasket().collect { basketModels ->
            val idList = basketModels.map { it.shipmentId }
            val basketShipment = database.getShipmentByIdsList(idList)
            val amountedShipment = basketShipment.map { bs ->
                bs.copy(amount = basketModels.filter { bm -> bm.shipmentId == bs.packId }
                    .first().amount)
            }
            dB.collection("bills/$user/items").document().set(
                DatedShipment(
                    date,
                    time,
                    amountedShipment.map {
                        DraftModel(
                            it.packId,
                            it.name,
                            it.barcode.body,
                            it.price.amount,
                            it.price.bonus,
                            it.amount
                        )
                    })
            ).await()
            var result: Long = 0
            amountedShipment.forEach {
                result = (it.price.amount - it.price.bonus) * it.amount
                basketRepository.deleteFromBasket(it.packId)
            }
            emit(result)
        }
    }

}