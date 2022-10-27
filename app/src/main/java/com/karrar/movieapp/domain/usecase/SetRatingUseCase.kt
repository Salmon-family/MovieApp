package com.karrar.movieapp.domain.usecase

import com.karrar.movieapp.data.repository.MovieRepository
import com.karrar.movieapp.domain.mappers.MovieMappersContainer
import com.karrar.movieapp.domain.models.RatingStatus
import javax.inject.Inject

class SetRatingUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val movieMappersContainer: MovieMappersContainer,
    private val getSessionIDUseCase: GetSessionIDUseCase,
) {

    suspend operator fun invoke(movieId: Int, value: Float): RatingStatus {
        val sessionId = getSessionIDUseCase() ?: ""
        val response = movieRepository.setRating(movieId, value, sessionId)
        return if (response != null) {
            movieMappersContainer.ratingStatusMoviesMapper.map(response)
        } else {
            throw Throwable("Not Success")
        }
    }
}