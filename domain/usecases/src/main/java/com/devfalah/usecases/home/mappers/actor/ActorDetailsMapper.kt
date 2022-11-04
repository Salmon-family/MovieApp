package com.devfalah.usecases.home.mappers.actor

import com.thechance.remote.response.actor.ActorDto
import com.devfalah.usecases.mappers.Mapper
import com.devfalah.models.ActorDetails
import com.thechance.repository.BuildConfig
import javax.inject.Inject

class ActorDetailsMapper @Inject constructor() :
    Mapper<ActorDto, ActorDetails> {
    override fun map(input: ActorDto): ActorDetails {

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