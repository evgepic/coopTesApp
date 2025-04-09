package com.example.cooptesapp.base

import com.example.cooptesapp.database.AppDataBase

interface DataBaseInstance {
    fun getDataBaseInstance(): AppDataBase
}