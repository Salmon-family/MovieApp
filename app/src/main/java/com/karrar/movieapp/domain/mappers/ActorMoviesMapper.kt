package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.data.remote.response.CastDto
import com.karrar.movieapp.domain.models.Actor
import com.karrar.movieapp.domain.models.Media
import com.karrar.movieapp.utilities.Constants.IMAGE_BASE_PATH
import javax.inject.Inject

class ActorMoviesMapper @Inject constructor() : Mapper<CastDto, Media> {
    override fun map(input: CastDto): Media {
        return Media(
            input.id ?: 0,
            IMAGE_BASE_PATH + input.posterPath,
            input.name ?: "unknown"
        )
    }
}