package com.example.cooptesapp.api

class AddToBasketUseCase(private val basketRepository: BasketRepository) {

    fun addToBasket(packId: Long, amount: Long) = basketRepository.addToBasket(packId, amount)

}