package com.example.cooptesapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cooptesapp.api.imp.BasketRepositoryImp
import com.example.cooptesapp.api.imp.DataBaseRepositoryRoomImp
import com.example.cooptesapp.base.DataBaseInstance
import com.example.cooptesapp.database.DataBaseRepositoryInstance
import com.example.cooptesapp.models.domain.Shipment
import com.example.cooptesapp.usecases.DeleteFromBasketUseCase
import com.example.cooptesapp.usecases.DoPaymentBasketUseCase
import com.example.cooptesapp.usecases.GetBasketUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class BasketViewModel(application: Application) :
    AndroidViewModel(application) {

    var list: MutableLiveData<List<Shipment>> = MutableLiveData()
    val doPaymentAmount: MutableLiveData<Long> = MutableLiveData()
    private val basketRepImp = BasketRepositoryImp()
    private val dataBaseRepository = DataBaseRepositoryRoomImp(
        DataBaseRepositoryInstance(
            (application as DataBaseInstance).getDataBaseInstance()
                .barcodeDao(),
            (application as DataBaseInstance).getDataBaseInstance().packDao(),
            (application as DataBaseInstance).getDataBaseInstance()
                .packPriceDao(),
            (application as DataBaseInstance).getDataBaseInstance().unitDao()
        )
    )
    private val getBasketUseCase =
        GetBasketUseCase(
            basketRepImp, dataBaseRepository
        )
    private val deleteFromBasketUseCase = DeleteFromBasketUseCase(basketRepImp)
    private val doPaymentBasketUseCase = DoPaymentBasketUseCase(basketRepImp, dataBaseRepository)

    fun getBasketItems() {
        viewModelScope.launch {
            getBasketUseCase.getBasket().collect {
                list.value = it
            }
        }
    }

    fun deleteItem(packId: Long) {
        deleteFromBasketUseCase.deleteFromBasket(packId)
        list.value = list.value?.filter { it.packId != packId }
    }

    fun doPayment() {
        val currentDate = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date())
        val currentTime: String =
            SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())
        viewModelScope.launch {
            doPaymentBasketUseCase.doPayment(currentTime, currentDate)
                .flowOn(Dispatchers.IO)
                .collect {
                    doPaymentAmount.value = it
                    list.value = emptyList()
                }
        }
    }

}
