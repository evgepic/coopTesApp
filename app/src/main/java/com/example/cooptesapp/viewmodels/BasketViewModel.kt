package com.example.cooptesapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cooptesapp.api.BasketRepositoryImp
import com.example.cooptesapp.api.DataBaseRepositoryRoomImp
import com.example.cooptesapp.api.DeleteFromBasketUseCase
import com.example.cooptesapp.api.GetBasketUseCase
import com.example.cooptesapp.base.DataBaseInstance
import com.example.cooptesapp.database.DataBaseRepositoryInstance
import com.example.cooptesapp.models.domain.Shipment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class BasketViewModel(application: Application) :
    AndroidViewModel(application) {

    var list: MutableLiveData<List<Shipment>> = MutableLiveData()
    private val basketRepImp = BasketRepositoryImp()
    private val getBasketUseCase =
        GetBasketUseCase(
            basketRepImp, DataBaseRepositoryRoomImp(
                DataBaseRepositoryInstance(
                    (application as DataBaseInstance).getDataBaseInstance()
                        .barcodeDao(),
                    (application as DataBaseInstance).getDataBaseInstance().packDao(),
                    (application as DataBaseInstance).getDataBaseInstance()
                        .packPriceDao(),
                    (application as DataBaseInstance).getDataBaseInstance().unitDao()
                )
            )
        )
    private val deleteFromBasketUseCase = DeleteFromBasketUseCase(basketRepImp)


    fun getBasketItems() {
        viewModelScope.launch {
            getBasketUseCase.getBasket().flowOn(Dispatchers.Main).collect {
                list.value = it
            }
        }
    }

    fun deleteItem(packId: Long) {
        deleteFromBasketUseCase.deleteFromBasket(packId)
        list.value = list.value?.filter { it.packId != packId }
    }

}
