package com.example.cooptesapp.usecases

import com.example.cooptesapp.api.DataBaseRepository
import com.example.cooptesapp.models.domain.Shipment
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ShipmentsUseCase(private val dataBaseRepository: DataBaseRepository) {

    fun getAllShipments(): Flow<List<Shipment>> = flow {
        emit(dataBaseRepository.getShipments())
    }


}