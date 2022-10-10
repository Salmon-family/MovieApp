package com.karrar.movieapp.data.repository

import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.data.remote.service.MovieService
import com.karrar.movieapp.domain.mappers.GenreMapper
import com.karrar.movieapp.domain.mappers.ListMapper
import com.karrar.movieapp.domain.mappers.TVShowMapper
import com.karrar.movieapp.domain.models.Genre
import com.karrar.movieapp.domain.models.Media
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SeriesRepositoryImp @Inject constructor(
    private val service: MovieService,
    private val mediaMapper: TVShowMapper,
    private val genreMapper: GenreMapper
) : BaseRepository(), SeriesRepository {

    override fun getOnTheAir(): Flow<State<List<Media>>> {
        return wrap({ service.getOnTheAir(1) }, { response ->
            response.items?.map { mediaMapper.map(it) } ?: emptyList()
        })
    }

    override suspend fun getOnTheAir2(page: Int): List<Media> {
        return wrap2({ service.getOnTheAir(page) },
            { ListMapper(mediaMapper).mapList(it.items) }) ?: emptyList()

    }

    override fun getAiringToday(): Flow<State<List<Media>>> {
        return wrap({ service.getAiringToday() }, { response ->
            response.items?.map { mediaMapper.map(it) } ?: emptyList()
        })
    }

    override suspend fun getAiringToday2(): List<Media> {
        return wrap2({ service.getAiringToday() },
            { ListMapper(mediaMapper).mapList(it.items) }) ?: emptyList()
    }

    override fun getTopRatedTvShow(): Flow<State<List<Media>>> {
        return wrap({ service.getTopRatedTvShow() }, { response ->
            response.items?.map { mediaMapper.map(it) } ?: emptyList()
        })
    }

    override suspend fun getTopRatedTvShow2(): List<Media> {
        return wrap2({ service.getTopRatedTvShow() },
            { ListMapper(mediaMapper).mapList(it.items) }) ?: emptyList()
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