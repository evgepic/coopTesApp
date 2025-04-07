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
        val unitDao = (application as DataBaseInstance).getDataBaseInstance().unitDao()
        val packDao = (application as DataBaseInstance).getDataBaseInstance().packDao()
        val packPriceDao = (application as DataBaseInstance).getDataBaseInstance().packPriceDao()
        val dbRep = DataBaseRepository(barcodeDao, packDao, packPriceDao, unitDao)
        val first = Shipment(
            "Pizza",
            Barcode("003300"),
            Dimension("KG"),
            1,
            Price(570, 50),
            700
        )
        viewmodel.insert(dbRep, listOf(first))
        viewmodel.getAll(dbRep)
    }

}