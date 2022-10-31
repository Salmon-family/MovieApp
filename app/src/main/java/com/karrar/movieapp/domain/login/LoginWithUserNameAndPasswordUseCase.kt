package com.karrar.movieapp.domain.login

import com.thechance.repository.DataClassParser
import com.thechance.repository.AccountRepository
import com.karrar.movieapp.domain.LoginStatus
import com.thechance.repository.remote.response.login.ErrorResponse
import javax.inject.Inject

class LoginWithUserNameAndPasswordUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
    private val getRequestTokenUseCase: GetRequestTokenUseCase,
    private val dataClassParser: DataClassParser,

    ) {
    suspend operator fun invoke(userName: String, password: String): LoginStatus {
        val token = getRequestTokenUseCase()
        val body = mapOf(
            "username" to userName,
            "password" to password,
            "request_token" to token,
        ).toMap()

        val validateRequestTokenWithLogin = accountRepository.validateRequestTokenWithLogin(body)

        return if (validateRequestTokenWithLogin.isSuccessful) {
            validateRequestTokenWithLogin.body()?.requestToken?.let { accountRepository.createSession(it) }
            LoginStatus.Success
        } else {
            val errorResponse = dataClassParser.parseFromJson(
                validateRequestTokenWithLogin.errorBody()?.string(), ErrorResponse::class.java
            )
            LoginStatus.Failure(errorResponse.statusMessage.toString())
        }
    }
}