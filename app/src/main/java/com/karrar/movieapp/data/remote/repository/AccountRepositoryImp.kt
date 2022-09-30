package com.karrar.movieapp.data.remote.repository

import com.karrar.movieapp.data.remote.Either
import com.karrar.movieapp.data.remote.response.login.ErrorResponse
import com.karrar.movieapp.data.remote.service.MovieService
import com.karrar.movieapp.utilities.DataClassParser
import javax.inject.Inject


class AccountRepositoryImp @Inject constructor(
    private val movieService: MovieService,
    private val dataClassParser: DataClassParser,
) : AccountRepository{


    override suspend fun loginWithUserNameANdPassword(
        userName: String,
        password: String,
    ): Either<Boolean, String> {

        val token = getRequestToken().toString()

        val body = mapOf<String, Any>(
            "username" to userName,
            "password" to password,
            "request_token" to token,
        ).toMap()

        return try {
            val validateWithLoginToken = movieService.validateRequestTokenWithLogin(body)
            if (validateWithLoginToken.isSuccessful) {
                validateWithLoginToken.body()?.requestToken?.let { createSession(it) }
                Either.Left(true)

            } else {
                val errorResponse =
                    dataClassParser.parseFromJson(validateWithLoginToken.errorBody()?.string()
                        .toString(), ErrorResponse::class.java)
                Either.Right(errorResponse.statusMessage.toString())
            }
        } catch (e: Exception) {
            (Either.Right(e.message.toString()))
        }

    }

    private suspend fun getRequestToken(): String? {
        return try {
            val tokenResponse = movieService.getRequestToken()
            tokenResponse.body()?.requestToken
        } catch (e: Exception) {
            e.message
        }

    }

    private suspend fun createSession(requestToken: String) {
        movieService.createSession(requestToken)
    }
}