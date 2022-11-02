package com.karrar.movieapp.domain.mappers.movie

import com.karrar.movieapp.data.remote.response.movie.RatingDto
import com.karrar.movieapp.domain.mappers.Mapper
import com.karrar.movieapp.domain.models.RatingStatus
import javax.inject.Inject

class RatingStatusMoviesMapper @Inject constructor() : Mapper<RatingDto, RatingStatus> {
    override fun map(input: RatingDto): RatingStatus {
        return RatingStatus(
            statusCode = input.statusCode ?: 0,
            statusMessage = input.statusMessage ?: "",
        )
    }
}