package com.karrar.movieapp.domain.login

import com.karrar.movieapp.data.DataClassParser
import com.karrar.movieapp.data.remote.response.login.ErrorResponse
import com.karrar.movieapp.data.repository.AccountRepository
import com.karrar.movieapp.domain.LoginState
import javax.inject.Inject

class LoginWithUserNameAndPasswordUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
    private val getRequestTokenUseCase: GetRequestTokenUseCase,
    private val dataClassParser: DataClassParser,

    ) {
    suspend operator fun invoke(userName: String, password: String): LoginState {
        val token = getRequestTokenUseCase()
        val body = mapOf(
            "username" to userName,
            "password" to password,
            "request_token" to token,
        ).toMap()

        val validateRequestTokenWithLogin = accountRepository.validateRequestTokenWithLogin(body)

        return if (validateRequestTokenWithLogin.isSuccessful) {
            validateRequestTokenWithLogin.body()?.requestToken?.let { accountRepository.createSession(it) }
            LoginState.Success
        } else {
            val errorResponse = dataClassParser.parseFromJson(
                validateRequestTokenWithLogin.errorBody()?.string(), ErrorResponse::class.java
            )
            LoginState.Error(errorResponse.statusMessage.toString())
        }
    }
}