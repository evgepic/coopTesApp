package com.example.cooptesapp.database

import com.example.cooptesapp.models.db.BarcodeEntity

class DataBaseRepository(private val barcodeDao: BarcodeDao) {

    suspend fun addBarcode(barcodeEntity: BarcodeEntity) = barcodeDao.insert(barcodeEntity)

    suspend fun getAll() = barcodeDao.getAll()

}