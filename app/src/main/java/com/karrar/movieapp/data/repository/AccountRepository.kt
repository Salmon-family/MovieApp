package com.karrar.movieapp.data.repository

import com.karrar.movieapp.data.remote.response.login.RequestTokenResponse
import com.karrar.movieapp.domain.models.Account
import com.karrar.movieapp.ui.UIState
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface AccountRepository {

    fun getSessionId(): String?

    suspend fun getRequestToken(): String

    suspend fun createSession(requestToken: String)

    suspend fun validateRequestTokenWithLogin(body : Map<String,Any>) : Response<RequestTokenResponse>

    suspend fun logout(): Boolean

    suspend fun getAccountDetails(sessionId: String): Account
}

