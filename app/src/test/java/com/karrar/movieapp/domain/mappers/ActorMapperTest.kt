package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.data.remote.response.actor.ActorDto
import com.karrar.movieapp.domain.models.Actor
import org.junit.jupiter.api.Assertions.*
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
            profilePath = "profilePath"
        )

        // when map function is called
        val actor = actorMapper.map(actorDTO)

        val expected = Actor(
            actorID = actor.actorID,
            actorName = actor.actorName,
            actorImage = actor.actorImage
        )

        // then return ActorMapper object with the same values
        assertEquals(expected, actor)
    }
}