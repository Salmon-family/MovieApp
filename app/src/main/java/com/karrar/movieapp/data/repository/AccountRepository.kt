package com.karrar.movieapp.data.repository

import com.karrar.movieapp.data.remote.response.account.AccountDto
import com.karrar.movieapp.data.remote.response.login.RequestTokenResponse
import retrofit2.Response

interface AccountRepository {

    fun getSessionId(): String?

    suspend fun getRequestToken(): String

    suspend fun createSession(requestToken: String)

    suspend fun validateRequestTokenWithLogin(body : Map<String,Any>) : Response<RequestTokenResponse>

    suspend fun logout()

    suspend fun getAccountDetails(sessionId: String): AccountDto?
}

