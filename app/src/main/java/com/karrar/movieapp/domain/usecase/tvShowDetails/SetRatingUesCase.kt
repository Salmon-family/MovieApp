package com.karrar.movieapp.domain.usecase.tvShowDetails

import com.karrar.movieapp.data.remote.response.movie.RatingDto
import com.karrar.movieapp.data.repository.SeriesRepository
import com.karrar.movieapp.domain.mappers.SeriesMapperContainer
import com.karrar.movieapp.domain.models.RatingStatus
import com.karrar.movieapp.ui.tvShowDetails.tvShowUIMapper.TvShowMapperContainer
import javax.inject.Inject

class SetRatingUesCase @Inject constructor(
    private val seriesRepository: SeriesRepository,
    private val getSessionIDUseCase: GetSessionIdUseCase,
    private val seriesMapperContainer: SeriesMapperContainer,
) {
    suspend operator fun invoke(tvShowId: Int, rating: Float) : RatingStatus? {
        val sessionId = getSessionIDUseCase() ?: ""
        val response = seriesRepository.setRating(tvShowId, rating, sessionId)
        return if (response != null) {
            seriesMapperContainer.ratingStatusTvShowMapper.map(response)
        } else {
            throw Throwable("Not Success")
        }
    }
}