package com.karrar.movieapp.domain.usecases.home.mappers

import com.karrar.movieapp.domain.usecases.mappers.Mapper
import com.thechance.remote.response.trailerVideosDto.TrailerDto
import com.karrar.movieapp.domain.models.Trailer
import com.karrar.movieapp.utilities.getKey
import javax.inject.Inject

class TrailerMapper @Inject constructor() : Mapper<TrailerDto, Trailer> {
    override fun map(input: com.thechance.remote.response.trailerVideosDto.TrailerDto): Trailer {
        return Trailer(input.results?.getKey() ?: "")
    }
}