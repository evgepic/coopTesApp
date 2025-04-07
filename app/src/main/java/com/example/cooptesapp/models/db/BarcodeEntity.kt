package com.example.cooptesapp.models.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "barcode")
data class BarcodeEntity(
    @PrimaryKey(autoGenerate = true)
    val barcodeId: Long = 0,
    val pack__id: Long,
    val body: String
)
