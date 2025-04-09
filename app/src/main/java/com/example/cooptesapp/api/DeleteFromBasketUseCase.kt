package com.example.cooptesapp.api

class DeleteFromBasketUseCase(private val basketRepository: BasketRepository) {
    fun deleteFromBasket(packId: Long) {
        basketRepository.deleteFromBasket(packId)
    }

}