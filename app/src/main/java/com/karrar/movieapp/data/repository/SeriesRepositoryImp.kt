package com.karrar.movieapp.data.repository

import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.data.remote.response.TVShowsDTO
import com.karrar.movieapp.data.remote.service.MovieService
import com.karrar.movieapp.domain.mappers.GenreMapper
import com.karrar.movieapp.domain.mappers.ListMapper
import com.karrar.movieapp.domain.mappers.SearchSeriesMapper
import com.karrar.movieapp.domain.mappers.TVShowMapper
import com.karrar.movieapp.domain.models.Genre
import com.karrar.movieapp.domain.models.Media
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SeriesRepositoryImp @Inject constructor(
    private val service: MovieService,
    private val mediaMapper: TVShowMapper,
    private val genreMapper: GenreMapper,
    private val seriesMapper: SearchSeriesMapper
) : BaseRepository(), SeriesRepository {

    override fun getOnTheAir(): Flow<State<List<Media>>> {
        return wrap({ service.getOnTheAir() }, { response ->
            response.items?.map { mediaMapper.map(it) } ?: emptyList()
        })
    }

    override suspend fun getOnTheAir2(): List<Media> {
        return wrap2({ service.getOnTheAir() },
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

    override suspend fun getTVShowsGenreList(): List<Genre> {
        return wrap2({ service.getGenreTvShowList() },
            { ListMapper(genreMapper).mapList(it.genres) })
    }

    override suspend fun getTvShowsByGenreID(genreId: Int): List<Media> {
        return wrap2({ service.getTvListByGenre(genreId) }, {
            ListMapper(mediaMapper).mapList(it.items)
        })
    }

    override suspend fun getAllTvShows(): List<Media> {
        return wrap2({ service.getAllTvShows() }, {
            ListMapper(mediaMapper).mapList(it.items)
        })
    }

    override suspend fun searchForSeries(query: String): List<Media> {
        return wrap2({ service.searchForSeries(query) }, { response ->
            response.items?.map { seriesMapper.map(it) } ?: emptyList()
        })
    }

}