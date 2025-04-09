package com.example.cooptesapp.api

import com.example.cooptesapp.models.domain.BasketModel
import kotlinx.coroutines.flow.Flow

interface BasketRepository {

    fun addToBasket(packId: Long, amount: Long)

    suspend fun getFromBasket() : Flow<List<BasketModel>>

    fun deleteFromBasket(packId: Long)

}