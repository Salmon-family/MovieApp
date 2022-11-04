package com.devfalah.usecases.home.mappers.movie

import com.thechance.remote.response.movie.RatingDto
import com.devfalah.usecases.mappers.Mapper
import com.devfalah.models.RatingStatus
import javax.inject.Inject

class RatingStatusMoviesMapper @Inject constructor() :
    Mapper<RatingDto, RatingStatus> {
    override fun map(input: RatingDto): RatingStatus {
        return RatingStatus(
            statusCode = input.statusCode ?: 0,
            statusMessage = input.statusMessage ?: "",
        )
    }
}