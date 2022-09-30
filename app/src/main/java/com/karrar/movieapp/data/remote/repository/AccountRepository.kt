package com.karrar.movieapp.data.remote.repository

import com.karrar.movieapp.data.remote.Either

interface AccountRepository {
    suspend fun loginWithUserNameANdPassword(
        userName: String,
        password: String,
    ): Either<Boolean, String>

}

