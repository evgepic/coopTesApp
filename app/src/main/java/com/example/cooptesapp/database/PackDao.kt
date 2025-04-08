package com.example.cooptesapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.cooptesapp.models.db.PackEntity

@Dao
interface PackDao {

    @Query(
        "SELECT * FROM pack JOIN unit ON pack.unit_id = unit.unitId JOIN pack_price ON pack_price.pack_id = pack.packId JOIN barcode ON barcode.pack__id = pack.packId"
    )
    fun getAllShipment(): List<ShipmentEntity>

    /* @Query(
        "SELECT * FROM pack JOIN unit ON pack.unit_id = unit.id"
    )
    fun getPackWithUnit(): Map<PackEntity, UnitEntity>*/

    @Insert
    fun insertPack(packEntity: PackEntity): Long

}