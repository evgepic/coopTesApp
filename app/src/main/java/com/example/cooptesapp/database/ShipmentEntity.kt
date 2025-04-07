package com.example.cooptesapp.database

import androidx.room.Embedded
import com.example.cooptesapp.models.db.BarcodeEntity
import com.example.cooptesapp.models.db.PackEntity
import com.example.cooptesapp.models.db.PackPriceEntity
import com.example.cooptesapp.models.db.UnitEntity

data class ShipmentEntity(
    @Embedded
    val packEntity: PackEntity,
    @Embedded
    val unitEntity: UnitEntity,
    @Embedded
    val priceEntity: PackPriceEntity,
    @Embedded
    val barcodeEntity: BarcodeEntity
)