package com.karrar.movieapp.data.repository

import com.karrar.movieapp.domain.models.Account
import com.karrar.movieapp.ui.UIState
import kotlinx.coroutines.flow.Flow

interface AccountRepository {

    fun getSessionId(): String?

    suspend fun loginWithUserNameANdPassword(
        userName: String,
        password: String,
    ): Boolean

    suspend fun logout(): Boolean

    suspend fun getAccountDetails(sessionId: String): Account
}

