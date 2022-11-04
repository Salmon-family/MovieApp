package com.karrar.movieapp.domain.usecases.home.mappers.movie

import com.thechance.remote.response.movie.RatingDto
import com.karrar.movieapp.domain.usecases.mappers.Mapper
import com.karrar.movieapp.domain.models.RatingStatus
import javax.inject.Inject

class RatingStatusMoviesMapper @Inject constructor() : Mapper<RatingDto, RatingStatus> {
    override fun map(input: com.thechance.remote.response.movie.RatingDto): RatingStatus {
        return RatingStatus(
            statusCode = input.statusCode ?: 0,
            statusMessage = input.statusMessage ?: "",
        )
    }
}