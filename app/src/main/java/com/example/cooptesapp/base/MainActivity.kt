package com.example.cooptesapp.base

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.cooptesapp.R
import com.example.cooptesapp.database.DataBaseRepository

class MainActivity : AppCompatActivity() {

    private val viewmodel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val barcodeDao = (application as DataBaseInstance).getDataBaseInstance().barcodeDao()
        val dbRep = DataBaseRepository(barcodeDao)
        viewmodel.insert(dbRep)
        viewmodel.getAll(dbRep)
    }

}