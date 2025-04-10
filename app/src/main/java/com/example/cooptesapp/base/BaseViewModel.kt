package com.example.cooptesapp.base

import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    val errorHandler: SingleLiveData<Throwable> = SingleLiveData()

}