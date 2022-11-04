package com.devfalah.usecases.home.getData.movie

import com.devfalah.models.Genre
import com.devfalah.usecases.home.mappers.GenreMapper
import com.thechance.repository.MovieRepository
import javax.inject.Inject

class GetMovieGenreListUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val genreMapper: GenreMapper
) {
    suspend operator fun invoke(): List<Genre> {
        val result = movieRepository.getMovieGenreList()
        result?.let {
            return it.map { genreMapper.map(it) }
        } ?: throw Throwable("error")
    }
}