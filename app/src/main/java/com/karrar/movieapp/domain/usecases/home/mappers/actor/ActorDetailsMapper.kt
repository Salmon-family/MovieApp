package com.karrar.movieapp.domain.usecases.home.mappers.actor

import com.karrar.movieapp.BuildConfig
import com.thechance.remote.response.actor.ActorDto
import com.karrar.movieapp.domain.usecases.mappers.Mapper
import com.karrar.movieapp.domain.models.ActorDetails
import javax.inject.Inject

class ActorDetailsMapper @Inject constructor() : Mapper<ActorDto, ActorDetails> {
    override fun map(input: com.thechance.remote.response.actor.ActorDto): ActorDetails {

        val gender = if (input.gender == 1) {
            "Female"
        } else {
            "Male"
        }

        return ActorDetails(
            input.id ?: 0,
            input.name ?: "",
            BuildConfig.IMAGE_BASE_PATH + input.profilePath,
            input.birthday ?: "",
            input.placeOfBirth ?: "unknown",
            input.biography ?: "",
            input.knownForDepartment ?: "-",
            gender,
        )
    }
}