package com.example.cooptesapp.base

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.cooptesapp.R
import com.example.cooptesapp.database.DataBaseRepositoryInstance
import com.example.cooptesapp.models.domain.Barcode
import com.example.cooptesapp.models.domain.Dimension
import com.example.cooptesapp.models.domain.Price
import com.example.cooptesapp.models.domain.Shipment


class MainActivity : AppCompatActivity() {

    private val viewmodel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val barcodeDao = (application as DataBaseInstance).getDataBaseInstance().barcodeDao()
        val unitDao = (application as DataBaseInstance).getDataBaseInstance().unitDao()
        val packDao = (application as DataBaseInstance).getDataBaseInstance().packDao()
        val packPriceDao = (application as DataBaseInstance).getDataBaseInstance().packPriceDao()
        val dbRep = DataBaseRepositoryInstance(barcodeDao, packDao, packPriceDao, unitDao)
        val first = Shipment(
            packId = 2,
            "Pizza",
            Barcode("003300"),
            Dimension("KG"),
            1,
            Price(570, 50),
            700, 0
        )
        val names = listOf<String>(
            "Сапог",
            "Весло",
            "Мука",
            "Гречка",
            "Сахар",
            "Шапка",
            "Стул",
            "Телефон",
            "Чай",
            "Кружка",
            "Ложка",
            "Вилка",
            "Бублик"
        )
        val barcodes = listOf<String>(
            "2234",
            "12334",
            "355554",
            "211123",
            "343222",
            "32344",
            "233211",
            "555555",
            "343232",
            "43535",
            "43433",
            "43434",
            "23233"
        )
        val dimension =
            listOf("шт", "шт.", "кг", "кг", "кг", "шт", "шт", "шт", "кг", "шт", "шт", "шт", "шт")
        val type = listOf(0, 0, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0)
        val priceList = listOf(
            Price(30000, 4000), Price(500000, 3230),
            Price(230, 20), Price(400, 40), Price(200, 20), Price(70000, 3000), Price(24000, 2100),
            Price(500, 20), Price(3000, 90), Price(400, 20), Price(3455, 32),
            Price(1230, 100), Price(22200, 13000)
        )
        val quant = listOf(1, 1, 1000, 1000, 1000, 1, 1, 1, 1000, 1, 1, 1, 1)
        val list = mutableListOf<Shipment>()
        for (i in 3..15) {
            list.add(
                Shipment(
                    packId = i.toLong(),
                    name = names.get(i - 3),
                    Barcode(barcodes.get(i - 3)),
                    Dimension(dimension.get(i - 3)),
                    type = type.get(i - 3).toLong(),
                    priceList.get(i - 3),
                    quantity = quant.get(i - 3).toLong(), 0
                )
            )
        }
        //viewmodel.insert(dbRep,list)
    }

}