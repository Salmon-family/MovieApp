package com.karrar.movieapp.domain.usecase

import com.karrar.movieapp.data.repository.MovieRepository
import com.karrar.movieapp.domain.mappers.movie.RatingStatusMoviesMapper
import com.karrar.movieapp.domain.models.RatingStatus
import javax.inject.Inject

class DeleteRatingUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val ratingStatusMoviesMapper: RatingStatusMoviesMapper,
    private val getSessionIDUseCase: GetSessionIDUseCase,
) {

    suspend operator fun invoke(movieId: Int): RatingStatus {
        val sessionId = getSessionIDUseCase() ?: ""
        val response = movieRepository.deleteRating(movieId, sessionId)
        return if (response != null) {
            ratingStatusMoviesMapper.map(response)
        } else {
            throw Throwable("Not Success")
        }
    }
}