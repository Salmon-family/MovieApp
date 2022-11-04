package com.karrar.movieapp.domain.usecases

import com.karrar.movieapp.data.repository.MovieRepository
import com.karrar.movieapp.data.repository.SeriesRepository
import com.karrar.movieapp.domain.enums.MediaType
import com.karrar.movieapp.domain.mappers.ReviewMapper
import com.karrar.movieapp.domain.models.Review
import javax.inject.Inject

class GetReviewsUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val seriesRepository: SeriesRepository,
    private val reviewMapper: ReviewMapper,
) {

    suspend operator fun invoke(type: MediaType, mediaID: Int): List<Review> {
        val reviews = when (type) {
            MediaType.MOVIE -> movieRepository.getMovieReviews(mediaID)
            MediaType.TV_SHOW -> seriesRepository.getTvShowReviews(mediaID)
        }
        return reviews?.let {
            it.map { reviewMapper.map(it) }
        } ?: throw Throwable("Not Success")
    }

}