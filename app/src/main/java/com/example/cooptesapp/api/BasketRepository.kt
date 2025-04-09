package com.example.cooptesapp.api

import kotlinx.coroutines.flow.Flow

interface BasketRepository {

    fun addToBasket(packId: Long, amount: Long)

    suspend fun getFromBasket() : Flow<List<BasketModel>>

    fun deleteFromBasket(packId: Long)

}