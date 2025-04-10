package com.example.cooptesapp.api

import com.example.cooptesapp.models.db.UserBio
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUserDataById() : Flow<UserBio>

}