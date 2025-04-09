package com.example.cooptesapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.cooptesapp.models.domain.DatedShipment
import com.example.cooptesapp.api.DraftRepository
import com.example.cooptesapp.api.imp.DraftRepositoryImp
import kotlinx.coroutines.launch

class ProfileViewModel(private val draftRepository: DraftRepository) : ViewModel() {

    var list: MutableLiveData<List<DatedShipment>> = MutableLiveData()

    fun getDraftsInfo() {
        viewModelScope.launch {
            draftRepository.getDraftsList().collect({
                list.value = it
            })
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                ProfileViewModel(
                    DraftRepositoryImp()
                )
            }
        }
    }

}