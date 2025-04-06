package com.example.cooptesapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cooptesapp.models.db.BarcodeEntity

@Database(entities = [BarcodeEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun barcodeDao(): BarcodeDao

}