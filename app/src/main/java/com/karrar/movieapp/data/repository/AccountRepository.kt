package com.karrar.movieapp.data.repository

import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.domain.models.Account
import kotlinx.coroutines.flow.Flow

interface AccountRepository {
    fun getSessionId(): Flow<String?>
    fun getAccountId(sessionId : String): Flow<State<Account>>
    suspend fun loginWithUserNameANdPassword(
        userName: String,
        password: String,
    ): Flow<State<Boolean>>

}

