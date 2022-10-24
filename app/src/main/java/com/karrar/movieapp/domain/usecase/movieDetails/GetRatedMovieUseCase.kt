package com.karrar.movieapp.domain.usecase.movieDetails

import com.karrar.movieapp.data.repository.MovieRepository
import com.karrar.movieapp.domain.models.Rated
import javax.inject.Inject

class GetRatedMovieUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {

    suspend operator fun invoke(accountId: Int, sessionId: String): List<Rated> {
        return movieRepository.getRatedMovie(accountId, sessionId)
    }
}