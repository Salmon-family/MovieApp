package com.devfalah.usecases.movieDetails

import com.devfalah.models.RatingStatus
import com.devfalah.usecases.home.mappers.movie.RatingStatusMoviesMapper
import com.thechance.repository.MovieRepository
import javax.inject.Inject

class SetRatingUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val ratingStatusMoviesMapper: RatingStatusMoviesMapper
) {

    suspend operator fun invoke(movieId: Int, value: Float): RatingStatus {
        val response = if (value == 0f) {
            movieRepository.deleteRating(movieId)
        } else {
            movieRepository.setRating(movieId, value)
        }
        return response?.let {
            ratingStatusMoviesMapper.map(response)
        } ?: throw Throwable("Not Success")
    }
}