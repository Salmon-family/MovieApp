package com.karrar.movieapp.data.repository

import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.domain.models.Account
import kotlinx.coroutines.flow.Flow

interface AccountRepository {
    fun getSessionId(): Flow<String?>
    suspend fun loginWithUserNameANdPassword(
        userName: String,
        password: String,
    ): Flow<State<Boolean>>

    suspend fun logout(): Flow<State<Boolean>>

    suspend fun getAccountDetails(sessionId: String): Account
}

