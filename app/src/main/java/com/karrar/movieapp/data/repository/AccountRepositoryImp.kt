package com.karrar.movieapp.data.repository

import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.data.remote.response.login.ErrorResponse
import com.karrar.movieapp.data.remote.service.MovieService
import com.karrar.movieapp.data.DataClassParser
import com.karrar.movieapp.data.local.AppConfiguration
import com.karrar.movieapp.domain.mappers.AccountMapper
import com.karrar.movieapp.domain.models.Account
import com.karrar.movieapp.utilities.DataStorePreferencesKeys
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class AccountRepositoryImp @Inject constructor(
    private val service: MovieService,
    private val appConfiguration: AppConfiguration,
    private val dataClassParser: DataClassParser,
    private val accountMapper: AccountMapper,
    ) : AccountRepository, BaseRepository() {
    override fun getSessionId(): Flow<String?> {
        return appConfiguration.readString(DataStorePreferencesKeys.SESSION_ID_KEY)
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

    override suspend fun logout(): Flow<State<Boolean>> {
        return flow {
            emit(State.Loading)
            try {
                getSessionId().collect{
                    val logout = service.logout(it.toString())
                    if (logout.isSuccessful){
                        appConfiguration.writeString(DataStorePreferencesKeys.SESSION_ID_KEY, "")
                        emit(State.Success(true))
                    } else {
                        emit(State.Error("There is an error"))
                    }
                }
            } catch (e: Exception) {
                emit(State.Error(e.message.toString()))
            }
        }
    }

    override suspend fun getAccountDetails(sessionId: String): Account {
        return wrap2({ service.getAccountDetails(sessionId) }, { accountMapper.map(it) })
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