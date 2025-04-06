package com.example.cooptesapp.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cooptesapp.database.DataBaseRepository
import com.example.cooptesapp.models.db.BarcodeEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {

    fun insert(dB: DataBaseRepository) {
        val barcode = BarcodeEntity(pack_id = 10, body = "First")
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                dB.addBarcode(barcode)
            }
        }
    }

    fun getAll(dB: DataBaseRepository) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
            }
        }
    }

}