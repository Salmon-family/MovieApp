package com.karrar.movieapp.domain.mappers.series

import android.media.Rating
import com.karrar.movieapp.data.remote.response.movie.RatingDto
import com.karrar.movieapp.domain.mappers.Mapper
import com.karrar.movieapp.domain.models.RatingStatus
import com.karrar.movieapp.ui.tvShowDetails.tvShowUIState.RatingUIState
import javax.inject.Inject

class RatingStatusTvShowMapper @Inject constructor(): Mapper<RatingDto, RatingStatus> {
    override fun map(input: RatingDto): RatingStatus {
        return RatingStatus(
            statusCode = input.statusCode ?: 0,
            statusMessage = input.statusMessage ?: "",
        )
    }
}