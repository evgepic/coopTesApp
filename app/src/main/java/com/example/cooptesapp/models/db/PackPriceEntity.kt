package com.example.cooptesapp.models.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pack_price")
data class PackPriceEntity(
    @PrimaryKey
    val id: Int,
    val pack_id: Int,
    val price: Int,
    val bonus: Int
)