package com.karrar.movieapp.data.remote.repository

import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.data.remote.service.SeriesService
import com.karrar.movieapp.domain.mappers.MediaMapper
import com.karrar.movieapp.domain.models.Media
import kotlinx.coroutines.flow.Flow

import javax.inject.Inject

class SeriesRepositoryImp @Inject constructor(
    private val seriesService: SeriesService,
    private val mapper: MediaMapper
) :
    BaseRepository(),SeriesRepository {

    override fun getOnTheAir(): Flow<State<List<Media>>> {
        return wrap({ seriesService.getOnTheAir() }, { baseResponse ->
            baseResponse.items?.map { mapper.map(it) } ?: emptyList()
        })
    }

    override fun getAiringToday(): Flow<State<List<Media>>> {
        return wrap({ seriesService.getAiringToday() }, { baseResponse ->
            baseResponse.items?.map { mapper.map(it) } ?: emptyList()
        })
    }

    override fun getTopRatedTvShow(): Flow<State<List<Media>>> {
        return wrap({ seriesService.getTopRatedTvShow() }, { baseResponse ->
            baseResponse.items?.map { mapper.map(it) } ?: emptyList()
        })
    }

    override fun getPopularTvShow(): Flow<State<List<Media>>> {
        return wrap({ seriesService.getPopularTvShow() }, { baseResponse ->
            baseResponse.items?.map { mapper.map(it) } ?: emptyList()
        })
    }

    override fun getLatestTvShow(): Flow<State<List<Media>>> {
        return wrap({ seriesService.getLatestTvShow() }, { baseResponse ->
            baseResponse.items?.map { mapper.map(it) } ?: emptyList()
        })
    }
}