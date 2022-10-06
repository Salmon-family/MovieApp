package com.karrar.movieapp.data.remote.repository

import com.karrar.movieapp.data.remote.State
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

abstract class BaseRepository {

    protected fun <I, O> wrap(function: suspend () -> Response<I>, mapper: (I) -> O): Flow<State<O>> {
        return flow {
            emit(State.Loading)
            try {
                val response = function()
                if (response.isSuccessful) {
                    val items = response.body()?.let { mapper(it) }
                    emit(State.Success(items))
                } else {
                    emit(State.Error(response.message()))
                }
            } catch (e: Exception) {
                emit(State.Error(e.message.toString()))
            }
        }
    }
}