package com.example.usecase.mappers.actor

import com.example.models.models.Actor
import com.example.usecase.mappers.Mapper
import com.thechance.repository.BuildConfig
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
