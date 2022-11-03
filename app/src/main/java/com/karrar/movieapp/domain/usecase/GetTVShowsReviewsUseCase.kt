package com.karrar.movieapp.domain.usecase

import com.karrar.movieapp.data.repository.SeriesRepository
import com.karrar.movieapp.domain.mappers.ReviewMapper
import com.karrar.movieapp.domain.models.Review
import javax.inject.Inject

class GetTVShowsReviewsUseCase @Inject constructor(
    private val seriesRepository: SeriesRepository,
    private val reviewMapper: ReviewMapper,
) {
    suspend operator fun invoke(showID: Int): List<Review> {
        return seriesRepository.getTvShowReviews(showID)?.let {
            it.map { reviewMapper.map(it) }
        } ?: throw Throwable("Not Success")
    }

}