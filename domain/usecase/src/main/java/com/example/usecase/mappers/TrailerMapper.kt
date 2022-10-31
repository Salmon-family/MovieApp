package com.example.usecase.mappers

import com.example.models.models.Trailer
import com.example.usecase.utilites.getKey
import com.thechance.repository.remote.response.trailerVideosDto.TrailerDto
import javax.inject.Inject

class TrailerMapper @Inject constructor() : Mapper<TrailerDto, Trailer> {
    override fun map(input: TrailerDto): Trailer {
        return Trailer(input.results?.getKey() ?: "")
    }
}
