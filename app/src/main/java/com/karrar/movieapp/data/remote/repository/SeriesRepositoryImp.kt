package com.karrar.movieapp.data.remote.repository

import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.data.remote.service.SeriesService
import com.karrar.movieapp.domain.mappers.GenreMapper
import com.karrar.movieapp.domain.mappers.MediaMapper
import com.karrar.movieapp.domain.models.Genre
import com.karrar.movieapp.domain.models.Media
import kotlinx.coroutines.flow.Flow

import javax.inject.Inject

class SeriesRepositoryImp @Inject constructor(
    private val seriesService: SeriesService,
    private val mapper: MediaMapper,
    private val genreMapper: GenreMapper
) :
    BaseRepository(), SeriesRepository {

    override fun getOnTheAir(): Flow<State<List<Media>>> {
        return wrap({ seriesService.getOnTheAir() }, {
            it.items?.map { mapper.map(it) } ?: emptyList()
        })
    }

    override fun getAiringToday(): Flow<State<List<Media>>> {
        return wrap({ seriesService.getAiringToday() }, {
            it.items?.map { mapper.map(it) } ?: emptyList()
        })
    }

    override fun getTopRatedTvShow(): Flow<State<List<Media>>> {
        return wrap({ seriesService.getTopRatedTvShow() }, {
            it.items?.map { mapper.map(it) } ?: emptyList()
        })
    }

    override fun getPopularTvShow(): Flow<State<List<Media>>> {
        return wrap({ seriesService.getPopularTvShow() }, {
            it.items?.map { mapper.map(it) } ?: emptyList()
        })
    }

    override fun getLatestTvShow(): Flow<State<List<Media>>> {
        return wrap({ seriesService.getLatestTvShow() }, {
            it.items?.map { mapper.map(it) } ?: emptyList()
        })
    }

    override fun getGenreList(): Flow<State<List<Genre>>> {
        return wrap({ seriesService.getGenreList() }, {
            it.genres?.map { genreMapper.map(it) } ?: emptyList()
        })
    }

    override fun getTvListByGenre(genreId: Int): Flow<State<List<Media>>> {
        return wrap({ seriesService.getTvListByGenre(genreId) }, {
            it.items?.map { mapper.map(it) } ?: emptyList()
        })
    }

    override fun getAllTvShows(page: Int): Flow<State<List<Media>>> {
        return wrap({ seriesService.getAllTvShows(page) }, {
            it.items?.map { mapper.map(it) } ?: emptyList()
        })
    }

}