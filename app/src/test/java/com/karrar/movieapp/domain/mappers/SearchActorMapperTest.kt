package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.data.remote.response.actor.ActorDto
import com.karrar.movieapp.domain.models.Media
import com.karrar.movieapp.utilities.Constants
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class SearchActorMapperTest {

    private var searchActorMapper = SearchActorMapper()

    @BeforeAll
    fun setUp() {
        searchActorMapper = SearchActorMapper()
    }

    @Test
    fun should_ReturnSearchActorMapper_when_EnterSearchActorDTO() {
        // given a SearchActorDTO object with random values
        val searchActorDTO = ActorDto(
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
        val searchActor = searchActorMapper.map(searchActorDTO)

        val expected = Media(
            mediaID = searchActor.mediaID,
            mediaImage = searchActor.mediaImage,
            mediaType = Constants.ACTOR,
            mediaName = searchActor.mediaName,
            mediaDate = searchActor.mediaDate,
            mediaRate = searchActor.mediaRate
        )

        // then return SearchActorMapper object with the same values
        assertEquals(expected, searchActor)
    }
}