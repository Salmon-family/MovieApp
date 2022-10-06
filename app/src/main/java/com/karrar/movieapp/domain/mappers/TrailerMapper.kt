package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.data.remote.response.trailerVideosDto.TrailerDto
import com.karrar.movieapp.domain.models.Trailer
import com.karrar.movieapp.utilities.getKey
import javax.inject.Inject

class TrailerMapper @Inject constructor():Mapper<TrailerDto, Trailer> {
    override fun map(input: TrailerDto): Trailer {
        return Trailer(
            input.results?.getKey()
        )
    }
}