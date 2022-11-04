package com.devfalah.usecases.home.mappers.series

import com.thechance.remote.response.movie.RatingDto
import com.devfalah.usecases.mappers.Mapper
import com.devfalah.models.RatingStatus
import javax.inject.Inject

class RatingStatusTvShowMapper @Inject constructor():
    Mapper<RatingDto, com.devfalah.models.RatingStatus> {
    override fun map(input: com.thechance.remote.response.movie.RatingDto): com.devfalah.models.RatingStatus {
        return com.devfalah.models.RatingStatus(
            statusCode = input.statusCode ?: 0,
            statusMessage = input.statusMessage ?: "",
        )
    }
}