package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.BuildConfig
import com.karrar.movieapp.data.remote.response.actorDetailsDto.CastDto
import com.karrar.movieapp.domain.models.Media
import javax.inject.Inject

class ActorMoviesMapper @Inject constructor() : Mapper<CastDto, Media> {
    override fun map(input: CastDto): Media {
        return Media(
            input.id ?: 0,
            BuildConfig.IMAGE_BASE_PATH + input.posterPath,
        )
    }

}