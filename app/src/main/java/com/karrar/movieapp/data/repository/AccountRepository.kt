package com.karrar.movieapp.data.repository

import com.karrar.movieapp.domain.models.Account
import com.karrar.movieapp.ui.UIState
import kotlinx.coroutines.flow.Flow

interface AccountRepository {
    suspend fun getSessionId(): String
    suspend fun loginWithUserNameANdPassword(
        userName: String,
        password: String,
    ): Flow<UIState<Boolean>>

    suspend fun logout(): Flow<UIState<Boolean>>

    suspend fun getAccountDetails(sessionId: String): Account
}

