package com.example.cooptesapp.api

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetBasketUseCase(
    private val basketRepository: BasketRepository,
    private val database: DataBaseRepository
) {

    fun getBasket() = flow {
        basketRepository.getFromBasket().collect { basketModels ->
            val idList = basketModels.map { it.shipmentId }
            val basketShipment = database.getShipmentByIdsList(idList)
            val amountedShipment = basketShipment.map { bs ->
                bs.copy(amount = basketModels.filter { bm -> bm.shipmentId == bs.packId }
                    .first().amount)
            }
            emit(amountedShipment)
        }
    }.flowOn(Dispatchers.IO)
}