package com.example.cooptesapp.models.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "barcode")
data class BarcodeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val pack_id: Int,
    val body: String
)
