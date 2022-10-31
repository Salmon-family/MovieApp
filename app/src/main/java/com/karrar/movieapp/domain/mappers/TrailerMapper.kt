package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.domain.models.Trailer
import com.karrar.movieapp.utilities.getKey
import com.thechance.repository.remote.response.trailerVideosDto.TrailerDto
import javax.inject.Inject

class TrailerMapper @Inject constructor() : Mapper<TrailerDto, Trailer> {
    override fun map(input: TrailerDto): Trailer {
        return Trailer(input.results?.getKey() ?: "")
    }
}