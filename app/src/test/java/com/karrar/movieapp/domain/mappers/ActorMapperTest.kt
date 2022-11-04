package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.BuildConfig
import com.thechance.local.mappers.ActorMapper
import com.devfalah.models.Actor
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class ActorMapperTest {

    private var actorMapper = com.thechance.local.mappers.ActorMapper()

    @BeforeAll
    fun setUp() {
        actorMapper = com.thechance.local.mappers.ActorMapper()
    }

    @Test
    fun should_ReturnActorMapper_when_EnterActorDTO() {
        // given a ActorDTO object with random values
        val actorDTO = com.thechance.remote.response.actor.ActorDto(
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
        val actor = actorMapper.map(actorDTO)

        val expected = com.devfalah.models.Actor(
            actorID = 1,
            actorName = "name",
            actorImage = BuildConfig.IMAGE_BASE_PATH + "profilePath",
        )

        // then return ActorMapper object with the same values
        assertEquals(expected, actor)
    }
}