package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.data.remote.response.MediaDto
import com.karrar.movieapp.data.remote.response.PersonDto
import com.karrar.movieapp.domain.models.Media
import com.karrar.movieapp.domain.models.Person
import com.karrar.movieapp.utilities.Constants
import javax.inject.Inject

class PersonMapper @Inject constructor() : Mapper<PersonDto, Media>{
    override fun map(input: PersonDto): Media {
        return Media(
            id = input.id,
            name = input.name,
            profileImage = Constants.IMAGE_BASE_PATH + input.profilePath,
            type = null,
            releaseDate = null,
            rate = null,
            imagePath = null
        )
    }
}
