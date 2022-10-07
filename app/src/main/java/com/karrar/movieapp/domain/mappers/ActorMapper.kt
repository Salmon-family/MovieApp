package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.data.remote.response.actor.ActorDto
import com.karrar.movieapp.domain.models.Actor
import com.karrar.movieapp.utilities.Constants
import javax.inject.Inject

class ActorMapper @Inject constructor() : Mapper<ActorDto, Actor> {
    override fun map(input: ActorDto): Actor {
        return Actor(
            actorID = input.id ?: 0,
            actorName = input.name,
            actorImage = Constants.IMAGE_BASE_PATH + input.profilePath
        )
    }
}