package com.karrar.movieapp.domain.mappers

import android.widget.TextView
import com.karrar.movieapp.data.remote.response.actorDetailsDto.ActorDetailsDto
import com.karrar.movieapp.data.remote.response.actorDetailsDto.CastDto
import com.karrar.movieapp.domain.models.ActorDetails
import com.karrar.movieapp.utilities.Constants.IMAGE_BASE_PATH
import javax.inject.Inject

class ActorDetailsMapper @Inject constructor(): Mapper<ActorDetailsDto, ActorDetails> {
    override fun map(input: ActorDetailsDto): ActorDetails {

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