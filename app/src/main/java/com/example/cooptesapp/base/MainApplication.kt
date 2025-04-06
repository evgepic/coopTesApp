package com.example.cooptesapp.base

import android.app.Application
import androidx.room.Room
import com.example.cooptesapp.database.AppDataBase

class MainApplication : Application(), DataBaseInstance {

    private lateinit var database: AppDataBase

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            applicationContext,
            AppDataBase::class.java, "database"
        ).build()
    }

    override fun getDataBaseInstance(): AppDataBase {
        return database
    }

}