package com.karrar.movieapp.domain.usecase.tvShowDetails

import com.karrar.movieapp.data.repository.SeriesRepository
import com.karrar.movieapp.domain.mappers.series.RatingStatusTvShowMapper
import com.karrar.movieapp.domain.models.RatingStatus
import javax.inject.Inject

class SetRatingUesCase @Inject constructor(
    private val seriesRepository: SeriesRepository,
    private val getSessionIDUseCase: GetSessionIdUseCase,
    val ratingStatusTvShowMapper: RatingStatusTvShowMapper
) {
    suspend operator fun invoke(tvShowId: Int, rating: Float): RatingStatus? {
        val sessionId = getSessionIDUseCase() ?: ""
        val response = seriesRepository.setRating(tvShowId, rating, sessionId)
        return if (response != null) {
            ratingStatusTvShowMapper.map(response)
        } else {
            throw Throwable("Not Success")
        }
    }
}
