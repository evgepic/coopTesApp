package com.example.cooptesapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.cooptesapp.models.db.BarcodeEntity

@Dao
interface BarcodeDao {

    @Query("SELECT * FROM barcode")
    fun getAll(): List<BarcodeEntity>

    @Insert
    fun insert(barcode: BarcodeEntity)

}