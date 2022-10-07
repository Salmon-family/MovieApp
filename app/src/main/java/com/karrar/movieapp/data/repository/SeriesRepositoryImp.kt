package com.karrar.movieapp.data.repository

import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.data.remote.service.MovieService
import com.karrar.movieapp.domain.mappers.GenreMapper
import com.karrar.movieapp.domain.mappers.MediaMapper
import com.karrar.movieapp.domain.models.Genre
import com.karrar.movieapp.domain.models.Media
import kotlinx.coroutines.flow.Flow

import javax.inject.Inject

class SeriesRepositoryImp @Inject constructor(
    private val service: MovieService,
    private val mediaMapper: MediaMapper,
    private val genreMapper: GenreMapper
) : BaseRepository(), SeriesRepository {

    override fun getOnTheAir(): Flow<State<List<Media>>> {
        return wrap({ service.getOnTheAir() }, { response ->
            response.items?.map { mediaMapper.map(it) } ?: emptyList()
        })
    }

    override fun getAiringToday(): Flow<State<List<Media>>> {
        return wrap({ service.getAiringToday() }, { response ->
            response.items?.map { mediaMapper.map(it) } ?: emptyList()
        })
    }

    override fun getTopRatedTvShow(): Flow<State<List<Media>>> {
        return wrap({ service.getTopRatedTvShow() }, { response ->
            response.items?.map { mediaMapper.map(it) } ?: emptyList()
        })
    }

    override fun getPopularTvShow(): Flow<State<List<Media>>> {
        return wrap({ service.getPopularTvShow() }, { response ->
            response.items?.map { mediaMapper.map(it) } ?: emptyList()
        })
    }

    override fun getLatestTvShows(): Flow<State<List<Media>>> {
        return wrap({ service.getLatestTvShow() }, { response ->
            response.items?.map { mediaMapper.map(it) } ?: emptyList()
        })
    }

    override fun getTVShowsGenreList(): Flow<State<List<Genre>>> {
        return wrap({ service.getGenreTvShowList() }, { response ->
            response.genres?.map { genreMapper.map(it) } ?: emptyList()
        })
    }

    override fun getTvShowsByGenreID(genreId: Int): Flow<State<List<Media>>> {
        return wrap({ service.getTvListByGenre(genreId) }, { response ->
            response.items?.map { mediaMapper.map(it) } ?: emptyList()
        })
    }

    override fun getAllTvShows(): Flow<State<List<Media>>> {
        return wrap({ service.getAllTvShows() }, { response ->
            response.items?.map { mediaMapper.map(it) } ?: emptyList()
        })
    }

}