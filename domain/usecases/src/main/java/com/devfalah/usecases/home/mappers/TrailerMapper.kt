package com.devfalah.usecases.home.mappers

import com.devfalah.models.Trailer
import com.devfalah.usecases.mappers.Mapper
import com.thechance.remote.response.trailerVideosDto.TrailerDto
import javax.inject.Inject

class TrailerMapper @Inject constructor() : Mapper<TrailerDto, Trailer> {
    override fun map(input: TrailerDto): Trailer {
        val keys = input.results?.map { if (it?.type == "Trailer")  it.key }.toString()
        return Trailer(keys)
    }
}