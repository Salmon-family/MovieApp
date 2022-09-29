package com.karrar.movieapp.data.remote.repository

import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.data.remote.service.SeriesService
import com.karrar.movieapp.domain.mappers.SeriesMapper
import com.karrar.movieapp.domain.models.Series
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class SeriesRepositoryImp @Inject constructor(
    private val seriesService: SeriesService,
    private val mapper: SeriesMapper
) :
    SeriesRepository {

    override fun getOnTheAir(): Flow<State<List<Series>>> {
        return wrap({ seriesService.getOnTheAir() }, {
            it.items?.map { mapper.map(it) } ?: emptyList()
        })
    }

    override fun getAiringToday(): Flow<State<List<Series>>> {
        return wrap({ seriesService.getAiringToday() }, {
            it.items?.map { mapper.map(it) } ?: emptyList()
        })
    }

    override fun getTopRatedTvShow(): Flow<State<List<Series>>> {
        return wrap({ seriesService.getTopRatedTvShow() }, {
            it.items?.map { mapper.map(it) } ?: emptyList()
        })
    }

    override fun getPopularTvShow(): Flow<State<List<Series>>> {
        return wrap({ seriesService.getPopularTvShow() }, {
            it.items?.map { mapper.map(it) } ?: emptyList()
        })
    }

    override fun getLatestTvShow(): Flow<State<List<Series>>> {
        return wrap({ seriesService.getLatestTvShow() }, {
            it.items?.map { mapper.map(it) } ?: emptyList()
        })
    }

    private fun <I, O> wrap(
        function: suspend () -> Response<I>,
        mapper: (I) -> O,
    ): Flow<State<O>> {
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