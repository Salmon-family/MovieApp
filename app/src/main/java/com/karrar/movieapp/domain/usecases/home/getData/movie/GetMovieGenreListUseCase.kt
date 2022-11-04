package com.karrar.movieapp.domain.usecases.home.getData.movie

import com.karrar.movieapp.domain.models.Genre
import com.karrar.movieapp.domain.usecases.home.mappers.GenreMapper
import javax.inject.Inject

class GetMovieGenreListUseCase @Inject constructor(
    private val movieRepository: com.thechance.repository.MovieRepository,
    private val genreMapper: GenreMapper
) {
    suspend operator fun invoke(): List<Genre> {
        val result = movieRepository.getMovieGenreList()
        result?.let {
            return it.map { genreMapper.map(it) }
        } ?: throw Throwable("error")
    }
}