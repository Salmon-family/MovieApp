package com.karrar.movieapp.domain.usecases.home.mappers.series

import com.thechance.remote.response.movie.RatingDto
import com.karrar.movieapp.domain.usecases.mappers.Mapper
import com.karrar.movieapp.domain.models.RatingStatus
import javax.inject.Inject

class RatingStatusTvShowMapper @Inject constructor(): Mapper<RatingDto, RatingStatus> {
    override fun map(input: com.thechance.remote.response.movie.RatingDto): RatingStatus {
        return RatingStatus(
            statusCode = input.statusCode ?: 0,
            statusMessage = input.statusMessage ?: "",
        )
    }
}