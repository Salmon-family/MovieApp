package com.karrar.movieapp.domain.usecase

import com.karrar.movieapp.data.repository.MovieRepository
import com.karrar.movieapp.domain.models.Actor
import javax.inject.Inject

class GetMovieCastUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {

    suspend operator fun invoke(movieId: Int): List<Actor> {
        return movieRepository.getMovieCast(movieId)
    }
}