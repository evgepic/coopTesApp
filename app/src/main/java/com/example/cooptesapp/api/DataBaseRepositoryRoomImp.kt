package com.example.cooptesapp.api

import com.example.cooptesapp.database.DataBaseRepositoryInstance
import com.example.cooptesapp.models.domain.Shipment
import com.example.cooptesapp.models.domain.toShipment

class DataBaseRepositoryRoomImp(private val database: DataBaseRepositoryInstance) :
    DataBaseRepository {

    override suspend fun getShipments(): List<Shipment> =
        database.getPacksWithUnit().map { it.toShipment() }

}