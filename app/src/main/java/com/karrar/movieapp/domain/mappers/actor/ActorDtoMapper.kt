package com.karrar.movieapp.domain.mappers.actor

import com.karrar.movieapp.BuildConfig
import com.karrar.movieapp.domain.mappers.Mapper
import com.karrar.movieapp.domain.models.Actor
import com.thechance.repository.remote.response.actor.ActorDto
import javax.inject.Inject

class ActorDtoMapper @Inject constructor() : Mapper<ActorDto, Actor> {
    override fun map(input: ActorDto): Actor {
        return Actor(
            actorID = input.id ?: 0,
            actorName = input.name ?: "unknown",
            actorImage = BuildConfig.IMAGE_BASE_PATH + input.profilePath
        )
    }
}