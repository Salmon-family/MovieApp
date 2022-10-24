package com.karrar.movieapp.domain.usecase

import com.karrar.movieapp.data.repository.MovieRepository
import com.karrar.movieapp.data.repository.SeriesRepository
import com.karrar.movieapp.domain.models.Genre
import com.karrar.movieapp.utilities.Constants
import com.karrar.movieapp.utilities.Constants.MOVIE_CATEGORIES_ID
import javax.inject.Inject

class GetGenreUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val seriesRepository: SeriesRepository
) {

    suspend operator fun invoke(mediaId: Int): List<Genre> {
        val genre = when (mediaId) {
            MOVIE_CATEGORIES_ID -> {
                movieRepository.getMovieGenreList()
            }
            else -> {
                seriesRepository.getTVShowsGenreList()
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
}