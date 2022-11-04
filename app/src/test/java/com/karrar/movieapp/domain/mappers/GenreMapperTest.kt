package com.karrar.movieapp.domain.mappers

import com.devfalah.models.Genre
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class GenreMapperTest {

    private lateinit var genreMapper: GenreMapper

    @BeforeAll
    fun setUp() {
        genreMapper = GenreMapper()
    }

    @Test
    fun should_ReturnGenreMapper_when_EnterGenreDto() {
        // given a GenreDto object with random values
        val genreDto = com.thechance.remote.response.genre.GenreDto(
            id = 1,
            name = "name"
        )

        // when map is called
        val result = genreMapper.map(genreDto)

        // expected
        val expectedGenre = com.devfalah.models.Genre(
            genreID = 1,
            genreName = "name"
        )

        // then the result should be a genre object with the same values
        assertEquals(expectedGenre, result)

    }
}