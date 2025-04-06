package com.example.cooptesapp.models.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pack")
data class PackEntity(
    @PrimaryKey
    val id: Int,
    val unit_id: Int,
    val text: String,
    val type: Int,
    val quant: Int
)
