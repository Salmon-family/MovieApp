package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.data.remote.response.genre.GenreDto
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
        val genreDto = GenreDto(
            id = 1,
            name = "name"
        )

        // when map is called
        val result = genreMapper.map(genreDto)

        // then the result should be a genre object with the same values
        assertEquals(genreDto.id, result.genreID)
        assertEquals(genreDto.name, result.genreName)
    }
}