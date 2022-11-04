package com.karrar.movieapp.domain.usecases.home.mappers.search

import com.karrar.movieapp.BuildConfig
import com.thechance.remote.response.actor.ActorDto
import com.karrar.movieapp.domain.usecases.mappers.Mapper
import com.karrar.movieapp.domain.models.Media
import com.karrar.movieapp.utilities.Constants
import javax.inject.Inject

class SearchActorMapper @Inject constructor() : Mapper<ActorDto, Media> {
    override fun map(input: com.thechance.remote.response.actor.ActorDto): Media {
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
