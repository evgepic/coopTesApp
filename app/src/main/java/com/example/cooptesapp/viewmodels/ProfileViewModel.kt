package com.example.cooptesapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.cooptesapp.api.DraftRepository
import com.example.cooptesapp.api.UserRepository
import com.example.cooptesapp.api.imp.DraftRepositoryImp
import com.example.cooptesapp.api.imp.UserRepositoryImp
import com.example.cooptesapp.models.domain.DatedShipment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val draftRepository: DraftRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    var list: MutableLiveData<List<DatedShipment>> = MutableLiveData()
    val profileData: MutableLiveData<String> = MutableLiveData()

    fun getDraftsInfo() {
        viewModelScope.launch {
            draftRepository.getDraftsList().collect({
                list.value = it
            })
        }
    }

    fun getUser() {
        viewModelScope.launch {
            userRepository.getUserDataById().flowOn(Dispatchers.IO)
                .catch {
                it
                }
                .collect {
                    profileData.value = it.name
                }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                ProfileViewModel(
                    DraftRepositoryImp(),
                    UserRepositoryImp()
                )
            }
        }
    }

}