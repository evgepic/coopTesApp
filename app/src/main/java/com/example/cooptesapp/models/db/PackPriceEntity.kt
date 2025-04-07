package com.example.cooptesapp.models.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pack_price")
data class PackPriceEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val pack_id: Long,
    val price: Long,
    val bonus: Long
)