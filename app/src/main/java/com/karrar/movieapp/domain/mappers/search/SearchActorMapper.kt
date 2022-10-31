package com.karrar.movieapp.domain.mappers.search

import com.karrar.movieapp.BuildConfig
import com.karrar.movieapp.domain.mappers.Mapper
import com.karrar.movieapp.domain.models.Media
import com.karrar.movieapp.utilities.Constants
import com.thechance.repository.remote.response.actor.ActorDto
import javax.inject.Inject

class SearchActorMapper @Inject constructor() : Mapper<ActorDto, Media> {
    override fun map(input: ActorDto): Media {
        return Media(
            input.id ?: 0,
            BuildConfig.IMAGE_BASE_PATH + input.profilePath,
            Constants.ACTOR,
            input.name ?: "",
            "",
            0F
        )
    }
}
