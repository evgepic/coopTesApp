package com.example.cooptesapp.api

import com.example.cooptesapp.models.domain.Shipment

interface DataBaseRepository {

    suspend fun getShipments(): List<Shipment>

    suspend fun getShipmentByIdsList(list: List<Long>): List<Shipment>

}