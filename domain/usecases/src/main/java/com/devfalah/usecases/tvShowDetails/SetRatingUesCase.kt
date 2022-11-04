package com.devfalah.usecases.tvShowDetails

import com.devfalah.usecases.home.mappers.series.RatingStatusTvShowMapper
import com.thechance.repository.SeriesRepository
import javax.inject.Inject

class SetRatingUesCase @Inject constructor(
    private val seriesRepository: SeriesRepository,
    private val ratingStatusTvShowMapper: RatingStatusTvShowMapper
) {
    suspend operator fun invoke(tvShowId: Int, rating: Float): com.devfalah.models.RatingStatus {
        val response = seriesRepository.setRating(tvShowId, rating)
        return if (response != null) {
            ratingStatusTvShowMapper.map(response)
        } else {
            throw Throwable("Not Success")
        }
    }
}
