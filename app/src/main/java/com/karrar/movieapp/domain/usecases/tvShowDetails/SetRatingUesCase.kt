package com.karrar.movieapp.domain.usecases.tvShowDetails

import com.karrar.movieapp.data.repository.SeriesRepository
import com.karrar.movieapp.domain.mappers.series.RatingStatusTvShowMapper
import com.karrar.movieapp.domain.models.RatingStatus
import javax.inject.Inject

class SetRatingUesCase @Inject constructor(
    private val seriesRepository: SeriesRepository,
    private val ratingStatusTvShowMapper: RatingStatusTvShowMapper
) {
    suspend operator fun invoke(tvShowId: Int, rating: Float): RatingStatus {
        val response = seriesRepository.setRating(tvShowId, rating)
        return if (response != null) {
            ratingStatusTvShowMapper.map(response)
        } else {
            throw Throwable("Not Success")
        }
    }
}
