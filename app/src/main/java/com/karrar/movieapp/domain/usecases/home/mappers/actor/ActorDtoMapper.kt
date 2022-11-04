package com.karrar.movieapp.domain.usecases.home.mappers.actor

import com.karrar.movieapp.BuildConfig
import com.thechance.remote.response.actor.ActorDto
import com.karrar.movieapp.domain.usecases.mappers.Mapper
import com.karrar.movieapp.domain.models.Actor
import javax.inject.Inject

class ActorDtoMapper @Inject constructor() : Mapper<ActorDto, Actor> {
    override fun map(input: com.thechance.remote.response.actor.ActorDto): Actor {
        return Actor(
            actorID = input.id ?: 0,
            actorName = input.name ?: "unknown",
            actorImage = BuildConfig.IMAGE_BASE_PATH + input.profilePath
        )
    }
}