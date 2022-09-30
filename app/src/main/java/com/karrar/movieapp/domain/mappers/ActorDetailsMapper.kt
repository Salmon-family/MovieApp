package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.data.remote.response.actorDetailsDto.ActorDetailsDto
import com.karrar.movieapp.domain.models.ActorDetails
import javax.inject.Inject

class ActorDetailsMapper @Inject constructor(): Mapper<ActorDetailsDto, ActorDetails> {
    override fun map(input: ActorDetailsDto): ActorDetails {
        return ActorDetails(
            input.id,
            input.name,
            input.profilePath,
            input.birthday,
            input.placeOfBirth,
            input.biography,
            input.knownForDepartment,
            input.gender
        )
    }
}