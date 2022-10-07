package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.data.remote.response.CastDto
import com.karrar.movieapp.domain.models.Actor
import com.karrar.movieapp.utilities.Constants
import javax.inject.Inject


class CastMapper @Inject constructor() : Mapper<CastDto, Actor> {
    override fun map(input: CastDto): Actor {
        return Actor(
            input.id ?: 0,
            Constants.IMAGE_BASE_PATH + input.profilePath,
            input.name ?: "unknown"
        )
    }
}