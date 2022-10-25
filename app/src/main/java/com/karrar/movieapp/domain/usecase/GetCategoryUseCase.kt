package com.karrar.movieapp.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.karrar.movieapp.data.remote.response.genre.GenreDto
import com.karrar.movieapp.data.repository.MovieRepository
import com.karrar.movieapp.data.repository.SeriesRepository
import com.karrar.movieapp.domain.mappers.GenreMapper
import com.karrar.movieapp.domain.mappers.ListMapper
import com.karrar.movieapp.domain.mappers.movie.MovieMapper
import com.karrar.movieapp.domain.mappers.series.TVShowMapper
import com.karrar.movieapp.domain.models.Genre
import com.karrar.movieapp.domain.models.Media
import com.karrar.movieapp.utilities.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCategoryUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val seriesRepository: SeriesRepository,
    private val movieMapper: MovieMapper,
    private val tvShowMapper: TVShowMapper,
    private val genreMapper: GenreMapper
) {

    operator fun invoke(mediaType: Int, genreID: Int): Flow<PagingData<Media>> {
        return when (mediaType) {
            Constants.MOVIE_CATEGORIES_ID -> {
                getMovies(genreID)
            }
            else -> {
                getTVShows(genreID)
            }
        }
    }

    private fun getMovies(genreID: Int): Flow<PagingData<Media>> {
        return if (genreID == Constants.FIRST_CATEGORY_ID) {
            movieRepository.getAllMovies().map { it ->
                it.map { movieMapper.map(it) }
            }
        } else {
            movieRepository.getMovieByGenre(genreID).map { it ->
                it.map { movieMapper.map(it) }
            }
        }
    }

    private fun getTVShows(genreID: Int): Flow<PagingData<Media>> {
        return if (genreID == Constants.FIRST_CATEGORY_ID) {
            seriesRepository.getAllTVShows().map { it ->
                it.map { tvShowMapper.map(it) }
            }
        } else {
            seriesRepository.getTVShowByGenre(genreID).map { it ->
                it.map { tvShowMapper.map(it) }
            }
        }
    }


    suspend fun getGenreList(mediaId: Int): List<Genre> {
        val genre = when (mediaId) {
            Constants.MOVIE_CATEGORIES_ID -> {
                mapGenre(movieRepository.getMovieGenreList2())
            }
            else -> {
                mapGenre(seriesRepository.getTVShowsGenreList2())
            }
        }
        return addAllGenre(genre)
    }

    private fun addAllGenre(genre: List<Genre>): List<Genre> {
        val allGenre = mutableListOf<Genre>()
        allGenre.add(Genre(Constants.FIRST_CATEGORY_ID, Constants.ALL))
        allGenre.addAll(genre)
        return allGenre.toList()
    }

    private fun mapGenre(genre: List<GenreDto>?): List<Genre> {
        return ListMapper(genreMapper).mapList(genre)
    }
}