package com.karrar.movieapp.data.repository

import com.karrar.movieapp.data.DataClassParser
import com.karrar.movieapp.data.local.AppConfiguration
import com.karrar.movieapp.data.remote.response.login.ErrorResponse
import com.karrar.movieapp.data.remote.service.MovieService
import com.karrar.movieapp.domain.mappers.AccountMapper
import com.karrar.movieapp.domain.models.Account
import com.karrar.movieapp.ui.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class AccountRepositoryImp @Inject constructor(
    private val service: MovieService,
    private val appConfiguration: AppConfiguration,
    private val dataClassParser: DataClassParser,
    private val accountMapper: AccountMapper,
) : AccountRepository, BaseRepository() {
    override suspend fun getSessionId(): String {
        return appConfiguration.getSessionId()
    }

    override suspend fun loginWithUserNameANdPassword(
        userName: String,
        password: String,
    ): Flow<UIState<Boolean>> {
        return flow {
            emit(UIState.Loading)
            try {
                val token = getRequestToken().toString()
                val body = hashMapOf<String, Any>(
                    "username" to userName,
                    "password" to password,
                    "request_token" to token,
                )

                val validateRequestTokenWithLogin = service.validateRequestTokenWithLogin(body)
                if (validateRequestTokenWithLogin.isSuccessful) {
                    validateRequestTokenWithLogin.body()?.requestToken?.let { createSession(it) }
                    emit(UIState.Success(true))
                } else {
                    val errorResponse =
                        dataClassParser.parseFromJson(validateRequestTokenWithLogin.errorBody()
                            ?.string(), ErrorResponse::class.java)
                    emit(UIState.Error(errorResponse.statusMessage.toString()))
                }
            } catch (e: Exception) {
                emit(UIState.Error(e.message.toString()))

            }
        }
    }

    override suspend fun logout(): Flow<UIState<Boolean>> {
        return flow {
            emit(UIState.Loading)
            try {
                val sessionId = getSessionId()
                val logout = service.logout(sessionId)
                if (logout.isSuccessful) {
                    appConfiguration.saveSessionId("")
                    emit(UIState.Success(true))
                } else {
                    emit(UIState.Error("There is an error"))
                }

            } catch (e: Exception) {
                emit(UIState.Error(e.message.toString()))
            }
        }
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
        appConfiguration.saveSessionId(sessionId)
    }

}