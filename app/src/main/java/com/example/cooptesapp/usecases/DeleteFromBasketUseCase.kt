package com.example.cooptesapp.usecases

import com.example.cooptesapp.api.BasketRepository

class DeleteFromBasketUseCase(private val basketRepository: BasketRepository) {
    fun deleteFromBasket(packId: Long) {
        basketRepository.deleteFromBasket(packId)
    }

}