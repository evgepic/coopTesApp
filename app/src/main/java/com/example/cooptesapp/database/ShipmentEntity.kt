package com.example.cooptesapp.database

import androidx.room.Embedded
import com.example.cooptesapp.models.db.PackEntity

data class ShipmentEntity(
    @Embedded
    val packEntity: PackEntity

)