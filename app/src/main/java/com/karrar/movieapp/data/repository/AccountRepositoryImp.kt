package com.karrar.movieapp.data.repository

import com.karrar.movieapp.data.DataClassParser
import com.karrar.movieapp.data.local.AppConfiguration
import com.karrar.movieapp.data.remote.response.login.ErrorResponse
import com.karrar.movieapp.data.remote.service.MovieService
import com.karrar.movieapp.domain.mappers.account.AccountMapper
import com.karrar.movieapp.domain.models.Account
import com.karrar.movieapp.utilities.DataStorePreferencesKeys
import javax.inject.Inject


class AccountRepositoryImp @Inject constructor(
    private val service: MovieService,
    private val appConfiguration: AppConfiguration,
    private val dataClassParser: DataClassParser,
    private val accountMapper: AccountMapper,
) : AccountRepository, BaseRepository() {

    override fun getSessionId(): String? {
        return appConfiguration.readString(DataStorePreferencesKeys.SESSION_ID_KEY)
    }

    override suspend fun loginWithUserNameANdPassword(
        userName: String,
        password: String
    ): Boolean {
        return try {
            val token = getRequestToken().toString()
            val body = mapOf<String, Any>(
                "username" to userName,
                "password" to password,
                "request_token" to token,
            ).toMap()

            val validateRequestTokenWithLogin = service.validateRequestTokenWithLogin(body)
            if (validateRequestTokenWithLogin.isSuccessful) {
                validateRequestTokenWithLogin.body()?.requestToken?.let { createSession(it) }
                true
            } else {
                val errorResponse = dataClassParser.parseFromJson(
                    validateRequestTokenWithLogin.errorBody()?.string(), ErrorResponse::class.java
                )
                throw Throwable(errorResponse.statusMessage)
            }
        } catch (e: Exception) {
            throw Throwable(e)
        }
    }

    override suspend fun logout(): Boolean {
        appConfiguration.writeString(DataStorePreferencesKeys.SESSION_ID_KEY, "")
        return true
    }

    override suspend fun getAccountDetails(sessionId: String): Account {
        return wrap({ service.getAccountDetails(sessionId) }, { accountMapper.map(it) })
    }

    private suspend fun getRequestToken(): String? {
        val tokenResponse = service.getRequestToken()
        return tokenResponse.body()?.requestToken
    }

    private suspend fun createSession(requestToken: String) {
        val sessionResponse = service.createSession(requestToken).body()
        if (sessionResponse?.success == true) {
            saveSessionId(sessionResponse.sessionId.toString())
        }
    }

    private suspend fun saveSessionId(sessionId: String) {
        appConfiguration.writeString(DataStorePreferencesKeys.SESSION_ID_KEY, sessionId)
    }

}