package com.karrar.movieapp.data.repository

import com.karrar.movieapp.data.DataClassParser
import com.karrar.movieapp.data.local.AppConfiguration
import com.karrar.movieapp.data.remote.response.account.AccountDto
import com.karrar.movieapp.data.remote.response.login.RequestTokenResponse
import com.karrar.movieapp.data.remote.service.MovieService
import com.karrar.movieapp.utilities.DataStorePreferencesKeys
import retrofit2.Response
import javax.inject.Inject


class AccountRepositoryImp @Inject constructor(
    private val service: MovieService,
    private val appConfiguration: AppConfiguration,
    private val dataClassParser: DataClassParser,
) : AccountRepository, BaseRepository() {

    override fun getSessionId(): String? {
        return appConfiguration.readString(DataStorePreferencesKeys.SESSION_ID_KEY)
    }


    override suspend fun logout() {
        appConfiguration.writeString(DataStorePreferencesKeys.SESSION_ID_KEY, "")
    }

    override suspend fun getAccountDetails(sessionId: String): AccountDto? {
        return service.getAccountDetails(sessionId).body()
    }

    override suspend fun getRequestToken(): String {
        val tokenResponse = service.getRequestToken()
        return tokenResponse.body()?.requestToken.toString()
    }

    override suspend fun validateRequestTokenWithLogin(body: Map<String, Any>) : Response<RequestTokenResponse>{
        return service.validateRequestTokenWithLogin(body)
    }

    override suspend fun createSession(requestToken: String) {
        val sessionResponse = service.createSession(requestToken).body()
        if (sessionResponse?.success == true) {
            saveSessionId(sessionResponse.sessionId.toString())
        }
    }

    private suspend fun saveSessionId(sessionId: String) {
        appConfiguration.writeString(DataStorePreferencesKeys.SESSION_ID_KEY, sessionId)
    }

}