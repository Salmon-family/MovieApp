package com.devfalah.usecases

import com.devfalah.models.Review
import com.devfalah.types.MediaType
import com.devfalah.usecases.home.mappers.ReviewMapper
import com.thechance.repository.MovieRepository
import com.thechance.repository.SeriesRepository
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