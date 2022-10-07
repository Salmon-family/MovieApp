package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.data.remote.response.actor.ActorDto
import com.karrar.movieapp.domain.models.ActorDetails
import com.karrar.movieapp.utilities.Constants.IMAGE_BASE_PATH
import javax.inject.Inject

class ActorDetailsMapper @Inject constructor(): Mapper<ActorDto, ActorDetails> {
    override fun map(input: ActorDto): ActorDetails {

        var gender = ""
        if (input.gender == 1){
            gender = "Female"
        } else if(input.gender == 2) {
            gender = "Male"
        }
        return ActorDetails(
            input.id,
            input.name,
            IMAGE_BASE_PATH + input.profilePath,
            input.birthday,
            input.placeOfBirth,
            input.biography,
            input.knownForDepartment,
            gender,
        )
    }
}