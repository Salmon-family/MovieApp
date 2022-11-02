package com.karrar.movieapp.ui.actorDetails

import com.karrar.movieapp.domain.mappers.Mapper
import com.karrar.movieapp.domain.models.ActorDetails
import javax.inject.Inject

class ActorDetailsUIMapper @Inject constructor() : Mapper<ActorDetails, ActorDetailsUIState> {
    override fun map(input: ActorDetails): ActorDetailsUIState {
        return ActorDetailsUIState(
            name = input.actorName,
            imageUrl = input.actorImage,
            gender = input.actorGender,
            birthday = input.actorBirthday,
            biography = input.actorBiography,
            placeOfBirth = input.actorPlaceOfBirth,
            knownFor = input.knownForDepartment,
        )
    }
}