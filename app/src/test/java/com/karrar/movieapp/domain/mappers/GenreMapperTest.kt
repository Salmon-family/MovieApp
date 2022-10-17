package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.data.remote.response.genre.GenreDto
import com.karrar.movieapp.domain.models.Genre
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class GenreMapperTest {

    private var genreMapper = GenreMapper()

    @BeforeAll
    fun setUp() {
        genreMapper = GenreMapper()
    }

    @Test
    fun should_ReturnGenreMapper_when_EnterGenreDTO() {
        // given a GenreDTO object with random values
        val genreDTO = GenreDto(
            id = 1,
            name = "name"
        )

        // when map function is called
        val genre = genreMapper.map(genreDTO)

        val expected = Genre(
            genreID = 1,
            genreName = "name"
        )

        // then return GenreMapper object with the same values
        assertEquals(expected, genre)
    }
}