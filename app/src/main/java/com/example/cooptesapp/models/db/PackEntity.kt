package com.example.cooptesapp.models.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pack")
data class PackEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val unit_id: Long,
    val name: String,
    val type: Long,
    val quant: Long
)
