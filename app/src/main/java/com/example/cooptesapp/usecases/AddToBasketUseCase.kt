package com.example.cooptesapp.usecases

import com.example.cooptesapp.api.BasketRepository

class AddToBasketUseCase(private val basketRepository: BasketRepository) {

    fun addToBasket(packId: Long, amount: Long) = basketRepository.addToBasket(packId, amount)

}