package com.karrar.movieapp.data.repository

import retrofit2.Response

abstract class BaseRepository {

    protected suspend fun <I, O> wrap(
        function: suspend () -> Response<I>,
        mapper: (I) -> O
    ): O {
        val response = function()
        return if (response.isSuccessful) {
            response.body()?.let { mapper(it) } ?: throw Throwable()
        } else {
            throw Throwable("response is not successful")
        }
    }
}