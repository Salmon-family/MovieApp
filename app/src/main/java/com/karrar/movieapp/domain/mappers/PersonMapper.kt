package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.data.remote.response.MediaDto
import com.karrar.movieapp.data.remote.response.PersonDto
import com.karrar.movieapp.domain.models.Media
import com.karrar.movieapp.domain.models.Person
import com.karrar.movieapp.utilities.Constants
import javax.inject.Inject

class PersonMapper @Inject constructor() : Mapper<PersonDto, Person>{
    override fun map(input: PersonDto): Person {
        return Person(
            id = input.id,
            name = input.name,
            profileImage = Constants.IMAGE_BASE_PATH + input.profilePath
        )
    }
}
