package com.karrar.movieapp.data.remote.repository

import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.data.remote.service.SeriesService
import com.karrar.movieapp.domain.mappers.SeriesMapper
import com.karrar.movieapp.domain.models.Series
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SeriesRepositoryImp @Inject constructor(private val seriesService: SeriesService) :
    SeriesRepository {

    private val mapper = SeriesMapper()

    override fun getOnTheAir(): Flow<State<List<Series>>> {
        return flow {
            emit(State.Loading)
            try {
                val response = seriesService.getOnTheAir()
                val items = response.body()?.items?.map { mapper.map(it) }
                emit(State.Success(items))
            } catch (throwable: Throwable) {
                emit(State.Error(throwable.message.toString()))
            }
        }
    }

    override fun getAiringToday(): Flow<State<List<Series>>> {
        return flow {
            emit(State.Loading)
            try {
                val response = seriesService.getAiringToday()
                val items = response.body()?.items?.map { mapper.map(it) }
                emit(State.Success(items))
            } catch (throwable: Throwable) {
                emit(State.Error(throwable.message.toString()))
            }
        }
    }

}