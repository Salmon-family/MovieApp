package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.data.remote.response.actor.ActorDto
import com.karrar.movieapp.data.remote.response.actor.ActorMoviesDto
import com.karrar.movieapp.domain.models.ActorDetails
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class ActorDetailsMapperTest {

    private var actorDetailsMapper = ActorDetailsMapper()

    @BeforeAll
    fun setUp() {
        actorDetailsMapper = ActorDetailsMapper()
    }

    @Test
    fun should_ReturnActorDetailsMapper_when_EnterActorDetailsDTO() {
        // given a ActorDetailsDTO object with random values
        val actorDTO = ActorDto(
            id = 1,
            name = "name",
            profilePath = "profilePath",
            biography = "biography",
            birthday = "birthday",
            deathday = "deathday",
            placeOfBirth = "placeOfBirth",
            popularity = 1.0,
            alsoKnownAs = listOf(),
            knownForDepartment = "knownForDepartment",
            gender = 1,
            adult = true,
            homepage = "homepage",
            imdbId = "imdbId",
        )

        // when map function is called
        val actorDetails = actorDetailsMapper.map(actorDTO)

        val expected = ActorDetails(
            actorID = actorDetails.actorID,
            actorName = actorDetails.actorName,
            actorImage = actorDetails.actorImage,
            actorBiography = actorDetails.actorBiography,
            actorBirthday = actorDetails.actorBirthday,
            actorPlaceOfBirth = actorDetails.actorPlaceOfBirth,
            actorGender = actorDetails.actorGender,
            knownForDepartment = actorDetails.knownForDepartment,
        )

        // then return ActorDetailsMapper object with the same values
        assertEquals(expected, actorDetails)
    }
}