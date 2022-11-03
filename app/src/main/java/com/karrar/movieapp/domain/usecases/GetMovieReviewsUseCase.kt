package com.karrar.movieapp.domain.usecases

import com.karrar.movieapp.data.repository.MovieRepository
import com.karrar.movieapp.domain.mappers.ReviewMapper
import com.karrar.movieapp.domain.models.Review
import javax.inject.Inject

class GetMovieReviewsUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val reviewMapper: ReviewMapper,
) {

    suspend operator fun invoke(movieId: Int): List<Review> {
        return movieRepository.getMovieReviews(movieId)?.let {
            it.map { reviewMapper.map(it) }
        } ?: throw Throwable("Not Success")
    }

}