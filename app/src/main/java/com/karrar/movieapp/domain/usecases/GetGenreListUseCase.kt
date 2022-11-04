package com.karrar.movieapp.domain.usecases

import com.karrar.movieapp.domain.models.Genre
import com.karrar.movieapp.domain.usecases.home.mappers.GenreMapper
import com.karrar.movieapp.domain.usecases.home.mappers.ListMapper
import com.karrar.movieapp.utilities.Constants
import javax.inject.Inject

class GetGenreListUseCase @Inject constructor(
    private val movieRepository: com.thechance.repository.MovieRepository,
    private val seriesRepository: com.thechance.repository.SeriesRepository,
    private val genreMapper: GenreMapper
) {

    suspend operator fun invoke(mediaId: Int): List<Genre> {
        val genre = when (mediaId) {
            Constants.MOVIE_CATEGORIES_ID -> {
                mapGenre(movieRepository.getMovieGenreList())
            }
            else -> {
                mapGenre(seriesRepository.getTVShowsGenreList())
            }
        }
        return setGenre(genre)
    }

    private fun setGenre(genre: List<Genre>): List<Genre> {
        val allGenre = mutableListOf<Genre>()
        allGenre.add(Genre(Constants.FIRST_CATEGORY_ID, Constants.ALL))
        allGenre.addAll(genre)
        return allGenre.toList()
    }

    private fun mapGenre(genre: List<com.thechance.remote.response.genre.GenreDto>?): List<Genre> {
        return ListMapper(genreMapper).mapList(genre)
    }
}