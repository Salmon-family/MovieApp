package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.data.remote.response.PersonDto
import com.karrar.movieapp.domain.models.Actor
import com.karrar.movieapp.utilities.Constants
import javax.inject.Inject

class ActorMapper @Inject constructor() : Mapper<PersonDto, Actor> {
    override fun map(input: PersonDto): Actor {
        return Actor(
            input.id,
            Constants.IMAGE_BASE_PATH + input.profilePath
        )
    }
}