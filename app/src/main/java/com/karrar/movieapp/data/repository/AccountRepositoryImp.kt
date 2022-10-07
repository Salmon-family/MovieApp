package com.karrar.movieapp.data.repository

import com.karrar.movieapp.data.local.DataStorePreferences
import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.data.remote.response.login.ErrorResponse
import com.karrar.movieapp.data.remote.service.MovieService
import com.karrar.movieapp.data.DataClassParser
import com.karrar.movieapp.utilities.DataStorePreferencesKeys
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class AccountRepositoryImp @Inject constructor(
    private val service: MovieService,
    private val dataStorePreferences: DataStorePreferences,
    private val dataClassParser: DataClassParser,
) : AccountRepository {
    override fun getSessionId(): Flow<String?> {
        return dataStorePreferences.readString(DataStorePreferencesKeys.SESSION_ID_KEY)
    }
    override suspend fun loginWithUserNameANdPassword(
        userName: String,
        password: String,
    ): Flow<State<Boolean>> {
        return flow {
            emit(State.Loading)
            try {
                val token = getRequestToken().toString()
                val body = mapOf<String, Any>(
                    "username" to userName,
                    "password" to password,
                    "request_token" to token,
                ).toMap()
                val validateRequestTokenWithLogin = service.validateRequestTokenWithLogin(body)
                if (validateRequestTokenWithLogin.isSuccessful) {
                    validateRequestTokenWithLogin.body()?.requestToken?.let { createSession(it) }
                    emit(State.Success(true))
                } else {
                    val errorResponse =
                        dataClassParser.parseFromJson(validateRequestTokenWithLogin.errorBody()
                            ?.string(), ErrorResponse::class.java)
                    emit(State.Error(errorResponse.statusMessage.toString()))
                }
            } catch (e: Exception) {
                emit(State.Error(e.message.toString()))

            }
        }
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
        dataStorePreferences.writeString(DataStorePreferencesKeys.SESSION_ID_KEY, sessionId)
    }

}