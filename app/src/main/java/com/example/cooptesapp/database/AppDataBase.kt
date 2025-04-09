package com.example.cooptesapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cooptesapp.models.db.BarcodeEntity
import com.example.cooptesapp.models.db.PackEntity
import com.example.cooptesapp.models.db.PackPriceEntity
import com.example.cooptesapp.models.db.UnitEntity

@Database(
    entities = [BarcodeEntity::class,
        UnitEntity::class,
        PackEntity::class,
        PackPriceEntity::class
    ], version = 1
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun barcodeDao(): BarcodeDao

    abstract fun unitDao(): UnitDao

    abstract fun packDao(): PackDao

    abstract fun packPriceDao(): PackPriceDao

}