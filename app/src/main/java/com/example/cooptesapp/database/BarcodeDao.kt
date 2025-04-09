package com.example.cooptesapp.database

import androidx.room.Dao
import androidx.room.Insert
import com.example.cooptesapp.models.db.BarcodeEntity

@Dao
interface BarcodeDao {

    @Insert
    fun insertBarcode(barcode: BarcodeEntity): Long


}