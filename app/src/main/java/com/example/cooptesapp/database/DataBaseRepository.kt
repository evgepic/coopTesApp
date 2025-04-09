package com.example.cooptesapp.database

import com.example.cooptesapp.models.db.BarcodeEntity
import com.example.cooptesapp.models.db.PackEntity
import com.example.cooptesapp.models.db.PackPriceEntity
import com.example.cooptesapp.models.db.UnitEntity

class DataBaseRepositoryInstance(
    private val barcodeDao: BarcodeDao,
    private val packDao: PackDao,
    private val packPriceDao: PackPriceDao,
    private val unitDao: UnitDao
) {
    fun addBarcode(barcodeEntity: BarcodeEntity) = barcodeDao.insertBarcode(barcodeEntity)

    fun addUnit(unitEntity: UnitEntity) = unitDao.insertUnit(unitEntity)

    fun addPackPrice(packPriceEntity: PackPriceEntity) =
        packPriceDao.insertPackPrice(packPriceEntity)

    fun addPack(packEntity: PackEntity) = packDao.insertPack(packEntity)

    fun getPacksWithUnit() = packDao.getAllShipment()

    fun getShipmentsByIds(idList: List<Long>) = packDao.getShipmentsByIds(idList)

}