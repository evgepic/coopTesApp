package com.example.cooptesapp.api

import com.example.cooptesapp.models.domain.DatedShipment
import kotlinx.coroutines.flow.Flow

interface DraftRepository {

    fun getDraftsList(): Flow<List<DatedShipment>>

}