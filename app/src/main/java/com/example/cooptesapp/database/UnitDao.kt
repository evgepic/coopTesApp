package com.example.cooptesapp.database

import androidx.room.Dao
import androidx.room.Insert
import com.example.cooptesapp.models.db.UnitEntity

@Dao
interface UnitDao {

    @Insert
    fun insertUnit(unitEntity: UnitEntity): Long


}