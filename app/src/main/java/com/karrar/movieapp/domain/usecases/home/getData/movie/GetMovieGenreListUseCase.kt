package com.karrar.movieapp.domain.usecase.home.getData.movie

import com.karrar.movieapp.data.repository.MovieRepository
import com.karrar.movieapp.domain.models.Genre
import javax.inject.Inject

class GetMovieGenreListUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    suspend operator fun invoke() : List<Genre>{
        return movieRepository.getMovieGenreList()
    }
}