package com.karrar.movieapp.data.repository

import androidx.paging.PagingConfig
import com.karrar.movieapp.data.remote.response.BaseListResponse
import retrofit2.Response

abstract class BaseRepository {

    val config = PagingConfig(pageSize = 100, prefetchDistance = 5, enablePlaceholders = false)

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