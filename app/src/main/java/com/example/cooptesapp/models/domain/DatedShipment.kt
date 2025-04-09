package com.example.cooptesapp.models.domain

data class DatedShipment(
    val date: String = "",
    val time: String = "",
    val list: List<DraftModel> = emptyList()
)