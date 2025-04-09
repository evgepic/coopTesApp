package com.example.cooptesapp.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cooptesapp.database.DataBaseRepositoryInstance
import com.example.cooptesapp.models.db.BarcodeEntity
import com.example.cooptesapp.models.db.PackEntity
import com.example.cooptesapp.models.db.PackPriceEntity
import com.example.cooptesapp.models.db.UnitEntity
import com.example.cooptesapp.models.domain.Shipment
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {

    fun insert(dB: DataBaseRepositoryInstance, shipmentsList: List<Shipment>) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                shipmentsList.forEach { item ->
                    val dimensionId = dB.addUnit(UnitEntity(unitName = item.dimension.name))
                    val packId = dB.addPack(
                        PackEntity(
                            unit_id = dimensionId,
                            packName = item.name,
                            type = 1,
                            quant = item.quantity
                        )
                    )
                    dB.addPackPrice(
                        PackPriceEntity(
                            price = item.price.amount,
                            bonus = item.price.bonus,
                            pack_id = packId
                        )
                    )
                    dB.addBarcode(BarcodeEntity(pack__id = packId, body = item.barcode.body))
                }
            }
        }
    }

}
