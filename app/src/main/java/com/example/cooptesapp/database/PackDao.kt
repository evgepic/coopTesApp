package com.example.cooptesapp.database

import androidx.room.Dao
import androidx.room.Insert
import com.example.cooptesapp.models.db.PackEntity

@Dao
interface PackDao {

    @Insert
    fun insertPack(packEntity: PackEntity): Long

}