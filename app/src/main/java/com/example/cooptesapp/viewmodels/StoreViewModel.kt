package com.example.cooptesapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cooptesapp.api.DataBaseRepositoryRoomImp
import com.example.cooptesapp.api.GetAllShipmentsUseCase
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
    private val getAllShipmentsUseCase: GetAllShipmentsUseCase = GetAllShipmentsUseCase(
        DataBaseRepositoryRoomImp(
            DataBaseRepositoryInstance(
                (application as DataBaseInstance).getDataBaseInstance().barcodeDao(),
                (application as DataBaseInstance).getDataBaseInstance().packDao(),
                (application as DataBaseInstance).getDataBaseInstance().packPriceDao(),
                (application as DataBaseInstance).getDataBaseInstance().unitDao()
            )
        )
    )

    fun getShipmentsList() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                getAllShipmentsUseCase.getAllShipments()
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

}