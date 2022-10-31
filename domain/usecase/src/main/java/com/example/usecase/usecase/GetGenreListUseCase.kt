package com.example.usecase.usecase

import com.example.models.models.Genre
import com.example.usecase.mappers.GenreMapper
import com.example.usecase.mappers.ListMapper
import com.example.usecase.utilites.Constants
import com.thechance.repository.MovieRepository
import com.thechance.repository.SeriesRepository
import com.thechance.repository.remote.response.genre.GenreDto
import javax.inject.Inject

class GetGenreListUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val seriesRepository: SeriesRepository,
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

    private fun mapGenre(genre: List<GenreDto>?): List<Genre> {
        return ListMapper(genreMapper).mapList(genre)
    }
}
