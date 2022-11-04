package com.karrar.movieapp.ui.actorDetails

import com.devfalah.usecases.mappers.Mapper
import com.devfalah.models.ActorDetails
import javax.inject.Inject

class ActorDetailsUIMapper @Inject constructor() :
    com.devfalah.usecases.mappers.Mapper<com.devfalah.models.ActorDetails, ActorDetailsUIState> {
    override fun map(input: com.devfalah.models.ActorDetails): ActorDetailsUIState {
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