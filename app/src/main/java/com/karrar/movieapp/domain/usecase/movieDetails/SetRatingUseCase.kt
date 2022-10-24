package com.karrar.movieapp.domain.usecase.movieDetails

import com.karrar.movieapp.data.remote.response.movie.RatingDto
import com.karrar.movieapp.data.repository.MovieRepository
import javax.inject.Inject

class SetRatingUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {

    suspend operator fun invoke(movieId: Int, value: Float, session_id: String): RatingDto {
        return movieRepository.setRating(movieId, value, session_id)
    }
}