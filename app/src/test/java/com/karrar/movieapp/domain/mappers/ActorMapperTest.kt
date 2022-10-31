package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.BuildConfig
import com.karrar.movieapp.domain.usecase.home.refreshData.mappers.ActorMapper
import com.karrar.movieapp.data.remote.response.actor.ActorDto
import com.karrar.movieapp.domain.models.Actor
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class ActorMapperTest {

    private var actorMapper = ActorMapper()

    @BeforeAll
    fun setUp() {
        actorMapper = ActorMapper()
    }

    @Test
    fun should_ReturnActorMapper_when_EnterActorDTO() {
        // given a ActorDTO object with random values
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
        val actor = actorMapper.map(actorDTO)

        val expected = Actor(
            actorID = 1,
            actorName = "name",
            actorImage = BuildConfig.IMAGE_BASE_PATH + "profilePath",
        )

        // then return ActorMapper object with the same values
        assertEquals(expected, actor)
    }
}