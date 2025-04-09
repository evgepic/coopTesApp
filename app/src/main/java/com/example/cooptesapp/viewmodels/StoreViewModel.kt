package com.example.cooptesapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cooptesapp.usecases.AddToBasketUseCase
import com.example.cooptesapp.api.imp.BasketRepositoryImp
import com.example.cooptesapp.api.imp.DataBaseRepositoryRoomImp
import com.example.cooptesapp.usecases.ShipmentsUseCase
import com.example.cooptesapp.base.DataBaseInstance
import com.example.cooptesapp.database.DataBaseRepositoryInstance
import com.example.cooptesapp.models.domain.Shipment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StoreViewModel(
    application: Application
) : AndroidViewModel(application) {

    val list: MutableLiveData<List<Shipment>> = MutableLiveData()
    private val shipmentsUseCase: ShipmentsUseCase = ShipmentsUseCase(
        DataBaseRepositoryRoomImp(
            DataBaseRepositoryInstance(
                (application as DataBaseInstance).getDataBaseInstance().barcodeDao(),
                (application as DataBaseInstance).getDataBaseInstance().packDao(),
                (application as DataBaseInstance).getDataBaseInstance().packPriceDao(),
                (application as DataBaseInstance).getDataBaseInstance().unitDao()
            )
        )
    )
    private val basketUseCase = AddToBasketUseCase(
        BasketRepositoryImp()
    )

    fun getShipmentsList() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                shipmentsUseCase.getAllShipments()
                    .catch {
                        it
                    }
                    .collect({
                        withContext(Dispatchers.Main) {
                            list.value = it
                        }
                    })
            }
        }
    }

    fun addToBasket(packId: Long, amount: Long) {
        basketUseCase.addToBasket(packId, amount)
    }

}