package com.devfalah.usecases.home.mappers.actor

import com.thechance.remote.response.actor.ActorDto
import com.devfalah.usecases.mappers.Mapper
import com.thechance.repository.BuildConfig
import javax.inject.Inject

class ActorDtoMapper @Inject constructor() : Mapper<ActorDto, com.devfalah.models.Actor> {
    override fun map(input: com.thechance.remote.response.actor.ActorDto): com.devfalah.models.Actor {
        return com.devfalah.models.Actor(
            actorID = input.id ?: 0,
            actorName = input.name ?: "unknown",
            actorImage = BuildConfig.IMAGE_BASE_PATH + input.profilePath
        )
    }
}