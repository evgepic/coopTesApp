package com.example.cooptesapp.models.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "unit")
data class UnitEntity(
    @PrimaryKey(autoGenerate = true)
    val unitId: Long = 0,
    val unitName: String
)