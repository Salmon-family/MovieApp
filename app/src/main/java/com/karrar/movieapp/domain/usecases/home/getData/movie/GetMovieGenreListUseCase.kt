package com.karrar.movieapp.domain.usecases.home.getData.movie

import com.karrar.movieapp.data.repository.MovieRepository
import com.karrar.movieapp.domain.mappers.GenreMapper
import com.karrar.movieapp.domain.models.Genre
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