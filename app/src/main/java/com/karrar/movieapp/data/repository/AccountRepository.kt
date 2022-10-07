package com.karrar.movieapp.data.repository

import com.karrar.movieapp.data.remote.State
import kotlinx.coroutines.flow.Flow

interface AccountRepository {
    fun getSessionId(): Flow<String?>
    suspend fun loginWithUserNameANdPassword(
        userName: String,
        password: String,
    ): Flow<State<Boolean>>

}

