package com.example.cooptesapp.database

import com.example.cooptesapp.models.db.BarcodeEntity
import com.example.cooptesapp.models.db.PackEntity
import com.example.cooptesapp.models.db.PackPriceEntity
import com.example.cooptesapp.models.db.UnitEntity

class DataBaseRepository(
    private val barcodeDao: BarcodeDao,
    private val packDao: PackDao,
    private val packPriceDao: PackPriceDao,
    private val unitDao: UnitDao
) {

    suspend fun addBarcode(barcodeEntity: BarcodeEntity) = barcodeDao.insertBarcode(barcodeEntity)

    suspend fun addUnit(unitEntity: UnitEntity) = unitDao.insertUnit(unitEntity)

    suspend fun addPackPrice(packPriceEntity: PackPriceEntity) =
        packPriceDao.insertPackPrice(packPriceEntity)

    suspend fun addPack(packEntity: PackEntity) = packDao.insertPack(packEntity)

    suspend fun getPacksWithUnit() = packDao.getPackWithUnit()

}