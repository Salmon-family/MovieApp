package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.data.remote.response.ActorDto
import com.karrar.movieapp.domain.models.Actor
import javax.inject.Inject

class ActorMapper @Inject constructor() : Mapper<ActorDto, Actor> {
    override fun map(input: ActorDto): Actor {
        return Actor(
            id = input.id,
            name = input.name,
            imagePath = input.profilePath
        )
    }
}