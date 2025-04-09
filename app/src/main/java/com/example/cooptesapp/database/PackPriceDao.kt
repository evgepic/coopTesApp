package com.example.cooptesapp.database

import androidx.room.Dao
import androidx.room.Insert
import com.example.cooptesapp.models.db.PackPriceEntity

@Dao
interface PackPriceDao {

    @Insert
    fun insertPackPrice(packPriceEntity: PackPriceEntity): Long

}