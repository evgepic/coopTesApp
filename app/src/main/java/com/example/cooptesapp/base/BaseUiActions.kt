package com.example.cooptesapp.base

interface BaseUiActions {

    fun showToast(stringResId: Int)

    fun startLoading()

    fun endLoading()

    fun showError(string: String)

}